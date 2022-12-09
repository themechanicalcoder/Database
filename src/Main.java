import entity.ColumnInfo;
import entity.Constraint;
import entity.DataType;
import entity.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Table table = new Table("test",  new ArrayList<>(Arrays.asList("firstName", "secondName")), new ArrayList<>(
                Arrays.asList(new ColumnInfo(DataType.STRING, Constraint.REQUIRED), new ColumnInfo(DataType.STRING, Constraint.REQUIRED))
        ));
        ArrayList<Object> record1 = new ArrayList<>(Arrays.asList("Gourav", "Roy"));
        ArrayList<Object> record2 = new ArrayList<>(Arrays.asList("Rishabh", "Roy"));
        table.insertRecord(record1);
        table.insertRecord(record2);
        ArrayList<ArrayList<Object>> result = table.getAllRecords();
        System.out.println(result.toString());
        result = table.filterOnColumn("secondName", "Roy");
        System.out.println(result.toString());

    }
}