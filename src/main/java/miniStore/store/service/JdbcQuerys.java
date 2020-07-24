package miniStore.store.service;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;



public class JdbcQuerys<T>  extends DataBaseConnections{

	public JdbcQuerys()
	{
		super();
	}
	public  List<T> queryForList(String sql ,Function<ResultSet, T> func,Object ...args)
	throws SQLException{
		List<T> list = new ArrayList();
		statment =  con.prepareStatement(sql);
		for(int i= 0; i< args.length;i++)
			statment.setObject(i+1, args[i]);
		
		result = statment.executeQuery();
	
		while (result.next())
		{
			list.add(func.apply(result));
		}
		return list;
		
	}
	
	public Optional<T> queryForObject(String sql , Function<ResultSet , T> func , Object ...args)
	throws SQLException{
	Optional <T> optional;
		statment =  con.prepareStatement(sql);
		for(int i= 0; i< args.length;i++)
			statment.setObject(i+1, args[i]);
		
		result = statment.executeQuery();
	
	optional = Optional.of(func.apply(result));
		 return optional;
	}
	public boolean update(String sql , Object ...args)
	throws SQLException{
		statment =  con.prepareStatement(sql);
		for(int i= 0; i< args.length;i++)
			statment.setObject(i+1, args[i]);
		
		return statment.execute();		
	}
}
