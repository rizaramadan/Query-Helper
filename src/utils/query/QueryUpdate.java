package utils.query;

/**
 * update set.
 * Query->update('sometable')->set({ {"someid", "4"} , {"somename","ray"} })->where({ {"someaddress","Indonesia"} , {"somephone","1234"} });
 *
 * @author ray
 */
public class QueryUpdate extends Query {

    private StringBuffer mBuffer;

    public QueryUpdate(DBMS pDBMSType){
        super(pDBMSType);
        mBuffer = new StringBuffer("update ");
    }

    /**
     * high level update. Use:
     * String[][] tMapValue = { {"name","bromo"} , {"filename","b_files"}};
     * QuerySelectInstance.updateMap("area",tMapValue,"area = 4");
     * @param pMapValue
     * @param pWhereClause
     */
    public QueryUpdate updateMap( String pTable, String[][] pMapValue,
                            String pWhereClause
                           ) {
        update(pTable);
        String [] tAttributes = new String[pMapValue.length];
        String [] tValues = new String[pMapValue.length];
        for(int i = 0; i < pMapValue.length; ++i) {
            tAttributes[i] = pMapValue[i][0];
            tValues[i] = pMapValue[i][1];
        }
        set(tAttributes,tValues);
        where(pWhereClause);
        return this;
        
    }

    public QueryUpdate update(String pTable) {
        mBuffer = escape(mBuffer, mDBMSType, pTable);
        return this;
    }
  
    public QueryUpdate set(String pAttribute, String pValue) {
        mBuffer.append(" set ").append(pAttribute).append(" = ");
        mBuffer = escape(mBuffer, mDBMSType, pValue);
        return this;
    }

    public QueryUpdate set(String[] pAttributes, String[] pValues) {
        mBuffer.append(" set ");
        for(int i = 0; i < pAttributes.length; ++i) {
            mBuffer.append(pAttributes[i]).append(" = ");
            mBuffer = escape(mBuffer, mDBMSType, pValues[i]);
            if(i != pAttributes.length - 1) mBuffer.append(',');
        }
        return this;
    }

    /**
     * Where's
     */
    public QueryUpdate where(String pExpression) {
        mBuffer.append(" where ");
        mBuffer = escape(mBuffer, mDBMSType, pExpression);
        return this;
    }

    @Override
    public String toString(){
        return mBuffer.toString();
    }
}
