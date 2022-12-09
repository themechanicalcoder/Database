package entity;

import validation.IntValidation;
import validation.RequiredValidation;
import validation.StringValidation;
import validation.Validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Table {
    String tableName;
    ArrayList<ColumnInfo> columnInfos;

    ArrayList<ArrayList<Object>> table;

    ArrayList<ArrayList<Validation>> columnValidations;
    ArrayList<String> columnNames;

    public Table(String tableName, ArrayList<String> columnNames, ArrayList<ColumnInfo> columnInfos) {
        this.table = new ArrayList<>();
        this.columnInfos = columnInfos;
        this.columnNames = columnNames;
        this.columnValidations = new ArrayList<>();
        for (ColumnInfo columnInfo : columnInfos) {
            columnValidations.add(new ArrayList<>(Arrays.asList(getDataTypeValidation(columnInfo.dataType),
                    getConstraintTypeValidation(columnInfo.constraint))));
        }
    }

    private  Validation getDataTypeValidation(DataType dataType) {
        switch (dataType) {
            case STRING:
                return  new StringValidation();
            case INTEGER:
                return new IntValidation();
            default:
                return null;
        }
    }

    private Validation getConstraintTypeValidation(Constraint constraint) {
        switch (constraint) {
            case REQUIRED:
                return new RequiredValidation();
            default:
                return null;
        }
    }

    public void insertRecord(ArrayList<Object> record) {
        boolean isValidRecord = true;
        for (int i = 0; i < columnNames.size(); i++) {
            if (!isValidEntry(i, (i >= record.size()  ? null : record.get(i)))) {
                isValidRecord = false;
            }
        }
        if (isValidRecord) {
            for (int i = record.size(); i < columnNames.size(); i++) {
                record.add(null);
            }
            table.add(record);
        }else {
            throw new RuntimeException("Invalid record");
        }
    }

    public ArrayList<ArrayList<Object>> getAllRecords(){
        return table;
    }

    public ArrayList<ArrayList<Object>> filterOnColumn(String columnName, String value) {
        ArrayList<ArrayList<Object>> result = new ArrayList<>();
        int index = columnNames.indexOf(columnName);
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).get(index).equals(value)) {
                result.add(table.get(i));
            }
        }
        return result;
    }


    private boolean isValidEntry(int index, Object object){
        return columnValidations.get(index).stream().filter(Objects::nonNull).allMatch(validation -> validation.validate(object));
    }

}
