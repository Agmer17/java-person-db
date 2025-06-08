package src.entity;

public enum Gender {
    LAKI_LAKI("Laki-laki"), PEREMPUAN("Perempuan");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static Gender genderFromLabel(String label) {
        for (Gender g : Gender.values()) {
            if (label.equalsIgnoreCase(g.getLabel())) {
                return g;
            }
        }
        return null;
    }
}
