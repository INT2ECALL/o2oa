package com.x.okr.assemble.control.jaxrs.okrworkdynamics;

import javax.servlet.http.HttpServletRequest;

import com.x.base.core.project.http.ActionResult;
import com.x.base.core.project.http.EffectivePerson;
import com.x.base.core.project.jaxrs.WoId;
import com.x.base.core.project.logger.Logger;
import com.x.base.core.project.logger.LoggerFactory;
import com.x.okr.assemble.control.jaxrs.okrworkdynamics.exception.ExceptionWorkDynamicsDelete;
import com.x.okr.assemble.control.jaxrs.okrworkdynamics.exception.ExceptionWorkDynamicsIdEmpty;

public class ActionDelete extends BaseAction {

	private static  Logger logger = LoggerFactory.getLogger( ActionDelete.class );
	
	protected ActionResult<Wo> execute( HttpServletRequest request,EffectivePerson effectivePerson, String id ) throws Exception {
		ActionResult<Wo> result = new ActionResult<>();
		
		if( id == null || id.isEmpty() ){
			Exception exception = new ExceptionWorkDynamicsIdEmpty();
			result.error( exception );
		}else{
			try{
				okrWorkDynamicsService.delete( id );
				result.setData( new Wo( id ));
			}catch( Exception e ){
				Exception exception = new ExceptionWorkDynamicsDelete( e, id );
				result.error( exception );
				logger.error( e, effectivePerson, request, null);
			}
		}
		return result;
	}
	
	public static class Wo extends WoId {
		public Wo( String id ) {
			this.setId( id );
		}
	}
}