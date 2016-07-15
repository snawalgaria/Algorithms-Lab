
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


class Node {
    int num;
    int wgt;
    List<Edge> incidentEdges;

    public Node(int id) {
        this.num = id;
        this.wgt = 0;
        this.incidentEdges = new ArrayList<Edge>();
    }
}

class Edge {
    int source;
    int destination;
    int wgt;
    int flow;
    int reverse;

    public Edge(int start, int end, int capacity, int rev) {
        this.source = start;
        this.destination = end;
        this.wgt = capacity;
        this.flow = 0;
        this.reverse = rev;
    }
}

class Tournament {
    int[][] matches;
    int numOfTeams;
    FootballTeam[] teams;

    public Tournament(int numberOfTeams) {
        this.numOfTeams = numberOfTeams;
        matches = new int[numberOfTeams + 1][numberOfTeams + 1];
        for (int i = 0; i < matches.length; i++) {
            Arrays.fill(matches[i], 0);
        }
        teams = new FootballTeam[numberOfTeams];
    }

    public void predictOutcomes() {
        FootballTeam[] temporaryTeams = new FootballTeam[numOfTeams];
        System.arraycopy(teams, 0, temporaryTeams, 0, numOfTeams);

        Arrays.sort(temporaryTeams);

        int maxCurrentWin = 0;
        for (FootballTeam t : temporaryTeams) {
            maxCurrentWin = t.presentWins > maxCurrentWin ? t.presentWins : maxCurrentWin;
        }

        for (FootballTeam t : temporaryTeams) {
            // If this team cannot attain even maximum current wins.
            if (t.maxNumOfWins < maxCurrentWin) {
                t.isWatchable = false;
                continue;
            }
            int teamNum = t.num;
            int numberOfMatchesBetweenOtherTeams = 0;

            for (int i = 1; i < matches.length; i++) {
                for (int j = 1; j < matches.length; j++) {
                    if (i != teamNum && j != teamNum && matches[i][j] != 0 && i < j) {
                        numberOfMatchesBetweenOtherTeams += matches[i][j];
                    }
                }
            }

            int targetNum = 600;
            Node source = new Node(0);
            Node target = new Node(targetNum);
            Graph graph = new Graph(0, targetNum);
            graph.nodes.put(0, source);
            graph.nodes.put(targetNum, target);
            int nodeCount = 199;

                        for (int i = 1; i < matches.length; i++) {
                if (i != teamNum) {
                    Node teamNode = new Node(i);
                    graph.nodes.put(i, teamNode);
                    teamNode.incidentEdges.add(new Edge(i, targetNum, teams[teamNum - 1].maxNumOfWins - teams[i - 1].presentWins, target.incidentEdges.size()));
                    target.incidentEdges.add(new Edge(targetNum, i, 0, teamNode.incidentEdges.size() - 1));
                }
                for (int j = 1; j < matches.length; j++) {
                    if (i != teamNum && j != teamNum) {
                        if (i > j && matches[i][j] > 0) {
                            Node matchNode = new Node(++nodeCount);
                            graph.nodes.put(nodeCount, matchNode);

                            matchNode.incidentEdges.add(new Edge(nodeCount, 0, 0, matchNode.incidentEdges.size()));
                            source.incidentEdges.add(new Edge(0, nodeCount, matches[i][j], matchNode.incidentEdges.size() - 1));

                            matchNode.incidentEdges.add(new Edge(nodeCount, i, Integer.MAX_VALUE, graph.nodes.get(i).incidentEdges.size()));
                            graph.nodes.get(i).incidentEdges.add(new Edge(i, nodeCount, 0, matchNode.incidentEdges.size() - 1));

                            matchNode.incidentEdges.add(new Edge(nodeCount, j, Integer.MAX_VALUE, graph.nodes.get(j).incidentEdges.size()));
                            graph.nodes.get(j).incidentEdges.add(new Edge(j, nodeCount, 0, matchNode.incidentEdges.size() - 1));
                        }
                    }
                }
            }

            graph.doDinitz();
            if (graph.maxFlow == numberOfMatchesBetweenOtherTeams) {
                t.isWatchable = true;
            } else {
                t.isWatchable = false;
            }
        }
    }
}

class FootballTeam implements Comparable<FootballTeam> {
    int num;
    int presentWins;
    int maxNumOfWins;
    boolean isWatchable;

    public FootballTeam(int id, int currentWin) {
        this.num = id;
        this.presentWins = currentWin;
        this.maxNumOfWins = currentWin;
        isWatchable = false;
    }

    @Override
    public int compareTo(FootballTeam t1) {
        return Integer.compare(maxNumOfWins, t1.maxNumOfWins);
    }
}

class Graph {
    Map<Integer, Node> nodes;

    int sourceNode, targetNode;
    int maxFlow;

    public Graph(int source, int target) {
        nodes = new HashMap<Integer, Node>();
        this.sourceNode = source;
        this.targetNode = target;
        maxFlow = 0;
    }

    /*Code taken from https://sites.google.com/site/indy256/algo/dinic_flow*/
    public void doDinitz() {
        Map<Integer, Integer> dist = new HashMap<Integer, Integer>();
        while (doBfs(sourceNode, targetNode, dist)) {
            Map<Integer, Integer> ptr = new HashMap<Integer, Integer>();
            for (Node v : nodes.values()) {
                ptr.put(v.num, 0);
            }
            while (true) {
                int df = doDinicDfs(ptr, dist, targetNode, sourceNode, Integer.MAX_VALUE);
                if (df == 0)
                    break;
                maxFlow += df;
            }
        }
    }

    private boolean doBfs(int sourceId, int targetId, Map<Integer, Integer> dist) {
        dist.clear();
        dist.put(sourceId, 0);
        int[] Q = new int[nodes.size()];
        int sizeOfQ = 0;
        Q[sizeOfQ++] = sourceId;
        for (int i = 0; i < sizeOfQ; i++) {
            int u = Q[i];
            for (Edge e : nodes.get(u).incidentEdges) {
                if (!dist.containsKey(e.destination) && e.flow < e.wgt) {
                    dist.put(e.destination, dist.get(u) + 1);
                    Q[sizeOfQ++] = e.destination;
                }
            }
        }
        return dist.containsKey(targetId);
    }

    private int doDinicDfs(Map<Integer, Integer> ptr, Map<Integer, Integer> dist, int targetId, int u, int flow) {
        if (u == targetId) {
            return flow;
        }
        for (; ptr.get(u) < nodes.get(u).incidentEdges.size(); ptr.replace(u, ptr.get(u) + 1)) {
            Edge e = nodes.get(u).incidentEdges.get(ptr.get(u));
            if (dist.containsKey(e.destination) && dist.containsKey(u) && (dist.get(e.destination) == dist.get(u) + 1) && e.flow < e.wgt) {
                int df = doDinicDfs(ptr, dist, targetId, e.destination, Math.min(flow, e.wgt - e.flow));
                if (df > 0) {
                    e.flow += df;
                    nodes.get(e.destination).incidentEdges.get(e.reverse).flow -= df;
                    return df;
                }
            }
        }
        return 0;
    }
}

public class Main {

    public static void main(String[] args) {
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCases;
        try {
            testCases = Integer.parseInt(bufferReader.readLine());

            Tournament[] tournaments = new Tournament[testCases];
            for (int tc = 0; tc < testCases; tc++) {
                String[] input = bufferReader.readLine().split(" ");
                int numOfTeams = Integer.parseInt(input[0]);
                int numOfMatches = Integer.parseInt(input[1]);
                Tournament tournament = new Tournament(numOfTeams);
                tournaments[tc] = tournament;

                String[] scores = bufferReader.readLine().split(" ");
                for (int teamNumber = 1; teamNumber <= numOfTeams; teamNumber++) {
                    tournament.teams[teamNumber - 1] = new FootballTeam(teamNumber, Integer.parseInt(scores[teamNumber - 1]));
                }

                for (int matchNumber = 0; matchNumber < numOfMatches; matchNumber++) {
                    String[] matchDetails = bufferReader.readLine().split(" ");
                    int teamA = Integer.parseInt(matchDetails[0]);
                    int teamB = Integer.parseInt(matchDetails[1]);
                    tournament.matches[teamA][teamB] += 1;
                    tournament.matches[teamB][teamA] += 1;
                    tournament.teams[teamA - 1].maxNumOfWins += 1;
                    tournament.teams[teamB - 1].maxNumOfWins += 1;
                }

                tournament.predictOutcomes();
                if (tc != testCases - 1)
                    bufferReader.readLine();
            }

            for (int caseNumber = 0; caseNumber < testCases; caseNumber++) {
                Tournament tournament = tournaments[caseNumber];
                bufferWriter.write("Case #" + (caseNumber + 1) + ":");
                for (FootballTeam t : tournament.teams) {
                    bufferWriter.write(String.format(" %s", t.isWatchable ? "yes" : "no"));
                }
                bufferWriter.write("\n");
            }
            bufferWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}