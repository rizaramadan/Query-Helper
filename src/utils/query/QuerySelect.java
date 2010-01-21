package utils.query;

/**
 * Select from
 * Query->select("someid")->from("sometable")->where({"somename" , "=", "ray"});
 * Query->select()
 */
public class QuerySelect extends Query
{

    private StringBuffer mBuffer;

    public QuerySelect(DBMS pDBMSType){
        super(pDBMSType);
        mBuffer = new StringBuffer("select ");
    }

    /**
     * Select from
     * Query->select("someid")->from("sometable")->where({"somename" , "=", "ray"});
     * Query->select()
     */
    public QuerySelect select(String pAttribute) {
        mBuffer = escape(mBuffer, mDBMSType, pAttribute);
        return this;
    }

    public QuerySelect select(String[] pAttribute) {
        for(String tElmt : pAttribute) {
            mBuffer = escape(mBuffer, mDBMSType, tElmt);
            if( !tElmt.equals(pAttribute[pAttribute.length -1]) ) {
                mBuffer.append( " , ");
            }
        }
        return this;
    }

    public QuerySelect from(String pTable) {
        mBuffer.append(" from ");
        mBuffer = escape(mBuffer, mDBMSType, pTable);
        return this;
    }

    public QuerySelect from(String[] pTable) {
        mBuffer.append(" from ");
        for(String pElmt : pTable) {
            mBuffer = escape(mBuffer, mDBMSType, pElmt);
            if(!pElmt.equals(pTable[pTable.length-1])) {
                mBuffer.append(" , ");
            }
        }
        return this;
    }

    /**
     * Where's
     */
    public QuerySelect where(String pExpression) {
        mBuffer.append(" where ");
        mBuffer = escape(mBuffer, mDBMSType, pExpression);
        return this;
    }

    @Override
    public String toString(){
        return mBuffer.toString();
    }
}
