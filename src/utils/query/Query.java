package utils.query;

public class Query
{
    protected DBMS mDBMSType;
    

    protected Query( DBMS pDBMSType ) {
        mDBMSType = pDBMSType;
    }

    /**
     * TODO: perfect it
     * escaping pInput and append it to pQueryBuffer
     * @param pQueryBuffer
     * @param pType
     * @param pInput
     * @return
     */
    static protected StringBuffer escape
                                    (StringBuffer pQueryBuffer,
                                     DBMS pType,
                                     String pInput) {
        if(pType == DBMS.DBMS_MYSQL) {
            pQueryBuffer.append(pInput/*.replace("'","\\'")*/);
        }
        return pQueryBuffer;
    }
   
    /**
     * Usage: 
     * Query.select(DBMS.DBMS_MYSQL,"idarea").from("area").where("name like '%romo%'");
     * will result "select idarea from area where name like '%romo%'"
     * @param pType DBType 
     * @param pAttribute The Attribute that wants to be selected
     * @return QuerySelect instance
     */
    public static QuerySelect select(DBMS pType, String pAttribute) {
        QuerySelect tQuery = new QuerySelect(pType);
        return tQuery.select(pAttribute);
    }

    /**
     * Usage: 
     * String [] tAttributes = {"idarea", "filename"};
     * String [] tTables = {"area", "area_detail"};
     * QuerySelect result = Query.select(DBMS.DBMS_MYSQL, tAttributes)
     *		.from(tTables).where("name like '%romo%'").toString();
     * will result "select idarea , filename from area , area_detail where name like '%romo%'"
     * @param pType  dbms type
     * @param pAttribute the attribute that wants to be selected
     * @return
     */
    public static QuerySelect select(DBMS pType,String[] pAttribute) {
        QuerySelect tQuery = new QuerySelect(pType);
        return tQuery.select(pAttribute);
    }

    /**
     * Usage: 
     * Query.update(DBMS.DBMS_MYSQL, "area").set("name", "'bromo'")
     *    .where("idarea = 1").toString();
     * will result "update area set name = 'bromo' where idarea = 1"
     * @param pType dbms type
     * @param pTable table name
     * @return
     */
    public static QueryUpdate update(DBMS pType, String pTable) {
        QueryUpdate tQuery = new QueryUpdate(pType);
        return tQuery.update(pTable);
    }

    /**
     * usage:
     * String[][] tMapValue = { {"name","'bromo'"} , {"filename","'b_files'"}};
     * Query.updateMap(DBMS.DBMS_MYSQL,"area",
     *           tMapValue,"idarea = 4").toString();
     * will result "update area set name = 'bromo',filename = 'b_files' where idarea = 4"
     * @param pType dbms type
     * @param pTable table name
     * @param pMapValue map of value that wants to be updated
     * @param pWhereClause the where clause
     * @return
     */
    public static QueryUpdate updateMap(DBMS pType, String pTable,
                               String[][] pMapValue, String pWhereClause){
        QueryUpdate tQuery = new QueryUpdate(pType);
        return tQuery.updateMap( pTable, pMapValue,pWhereClause);
    }
   
    /**
     * usage:
     * String [] tAttributes = {"name", "filename"};
     * String [] tValues = {"'bromo'", "'bromo_file'"};
     * Query.insertInto(DBMS.DBMS_MYSQL,"area")
     *				.values(tAttributes, tValues).toString();
     * will result: "insert into area(name,filename) values ('bromo','bromo_file')"
     * @param pType dbms type
     * @param pTable table name
     * @return
     */
    public static QueryInsert insertInto(DBMS pType, String pTable) {
        QueryInsert tQuery = new QueryInsert( pType );
        return tQuery.insertInto(pTable);
    }

    /**
     * usage:
     * String[][] tMapValue = { {"name","'bromo'"} , {"filename","'b_files'"}};
     * Query.insert(DBMS.DBMS_MYSQL, "area", tMapValue).toString();
     * will result: "insert into area(name,filename) values ('bromo','b_files')"
     * @param pType dbms type
     * @param pTable table name
     * @param pMapValue map of value that wants to be inserted
     * @return
     */
    public static QueryInsert insert(DBMS pType, String pTable,
                                    String[][] pMapValue){
        QueryInsert tQuery = new QueryInsert( pType );
        return tQuery.insert(pTable, pMapValue);
    }

    /**
     * usage:
     * String[] tAttributes = {"name","filename"};
     * String[][] tValues = {  {"'bromo'","'b'"} ,
     *                           {"'cangkuang'","'c'"},
     *                           {"'3'","'d'"}
     *                        };
     * Query.inserts(DBMS.DBMS_MYSQL,"area",tAttributes, tValues).toString();
     * will result "insert into area(name,filename) values ('bromo','b'),
     *                     ('cangkuang','c'),('3','d')"
     * @param pType DBMS type
     * @param pTable table name
     * @param pAttribute attributes
     * @param pValues values
     * @return QueryInsert Instance
     */
    public static QueryInsert inserts(DBMS pType,String pTable,
                                 String[] pAttribute,
                                 String[][] pValues ) {
        QueryInsert tQuery = new QueryInsert( pType );
        return tQuery.inserts(pTable,pAttribute, pValues);
    }
}
