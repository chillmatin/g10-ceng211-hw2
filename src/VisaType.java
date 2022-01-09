public enum VisaType {
    Tourist("11"),
    Worker("23"),
    Educational("25"),
    Immigrant("30");

    private final String typeCode;
    VisaType(String typeCode){
        this.typeCode = typeCode;
    }

    }
