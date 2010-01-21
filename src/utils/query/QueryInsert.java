package utils.query;

/**
 *
 * @author sangkuriang3
 */
public class QueryInsert extends Query {
    private StringBuffer mBuffer;

    public QueryInsert(DBMS pDBMSType){
        super(pDBMSType);
        mBuffer = new StringBuffer("insert into ");
    }

    /**
     * high level insert. Use:
     * 
     * @param pMapValue
     * @param pWhereClause
     */
    public QueryInsert insert( String pTable, String[][] pMapValue) {
        insertInto(pTable);
        String [] tAttributes = new String[pMapValue.length];
        String [] tValues = new String[pMapValue.length];
        for(int i = 0; i < pMapValue.length; ++i) {
            tAttributes[i] = pMapValue[i][0];
            tValues[i] = pMapValue[i][1];
        }
        values(tAttributes,tValues);
        return this;
    }

    public QueryInsert inserts( String pTable,String[] pAttribute, 
                                 String[][] pValues
                               ) {
        insertInto(pTable);
        mBuffer.append('(');
        for(int i = 0; i < pAttribute.length; ++i) {
            mBuffer.append(pAttribute[i]);
            if(i != pAttribute.length - 1) mBuffer.append(',');
        }
        mBuffer.append(") values ");
        
        for(int i = 0; i < pValues.length; ++i) {
            mBuffer.append('(');
            for(int j = 0; j < pValues[i].length; ++j) {
                mBuffer.append(pValues[i][j]);
                if(j != pValues[i].length - 1) mBuffer.append(',');
            }
            mBuffer.append(')');
            if(i != pValues.length - 1) mBuffer.append(',');
        }
        return this;
    }

    public QueryInsert insertInto(String pTable) {
        mBuffer.append(pTable);
        return this;
    }

    public QueryInsert values(String[] pAttribute, String[] pValue) {
        mBuffer.append('(');
        for(int i = 0; i < pAttribute.length; ++i) {
            mBuffer.append(pAttribute[i]);
            if(i != pAttribute.length - 1) mBuffer.append(',');
        }
        mBuffer.append(") values ");

        mBuffer.append('(');
        for(int i = 0; i < pValue.length; ++i) {
            mBuffer.append(pValue[i]);
            if(i != pValue.length - 1) mBuffer.append(',');
        }
        mBuffer.append(')');
        return this;
    }
    
    @Override
    public String toString(){
        return mBuffer.toString();
    }
}
