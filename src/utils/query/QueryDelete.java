package utils.query;

/**
 *
 * @author sangkuriang3
 */
public class QueryDelete extends Query {
    
    
    private StringBuffer mBuffer;

    public QueryDelete(DBMS pDBMSType){
        super(pDBMSType);
        mBuffer = new StringBuffer("delete from ");
    }

    public QueryDelete delete(String pTable) {
        mBuffer = escape(mBuffer, mDBMSType, pTable);
        return this;
    }

    public QueryDelete where(String pExpression) {
        mBuffer.append(" where ");
        mBuffer = escape(mBuffer, mDBMSType, pExpression);
        return this;
    }

    @Override
    public String toString(){
        return mBuffer.toString();
    }
}
