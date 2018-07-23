package idv.haojun.calculatorrecorder.model;

public class CalculatorRecord {
    public static final int ADD = 0;
    public static final int SUB = 1;
    public static final int MUL = 2;
    public static final int DIV = 3;
    private long id;
    private String value1;
    private String value2;
    private int operateType;
    private String result;

    public CalculatorRecord(){

    }

    public CalculatorRecord(String value1, String value2, int operateType, String result) {
        this.value1 = value1;
        this.value2 = value2;
        this.operateType = operateType;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public int getOperateType() {
        return operateType;
    }

    public String getOperateTypeText() {
        switch (operateType) {
            case ADD:
                return "+";
            case SUB:
                return "-";
            case MUL:
                return "x";
            case DIV:
                return "/";
            default:
                return "";
        }
    }

    public void setOperateType(int operateType) {
        this.operateType = operateType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
