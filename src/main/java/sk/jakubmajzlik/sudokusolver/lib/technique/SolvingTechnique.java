package sk.jakubmajzlik.sudokusolver.lib.technique;

public enum SolvingTechnique {
    SINGLE_CANDIDATE,
    SCANNING,
    ELIMINATION;

    public static String getTechniqueName(SolvingTechnique technique) {
        switch (technique) {
            case SINGLE_CANDIDATE:
                return "Single Candidate";
            case SCANNING:
                return "Scanning";
            case ELIMINATION:
                return "Elimination";
            default:
                return "Unknown";
        }
    }
}
