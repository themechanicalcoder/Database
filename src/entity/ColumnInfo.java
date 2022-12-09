package entity;

public class ColumnInfo {

    public ColumnInfo(DataType dataType, Constraint constraint) {
        this.dataType = dataType;
        this.constraint = constraint;
    }
    public DataType dataType;
    public Constraint constraint;
}
