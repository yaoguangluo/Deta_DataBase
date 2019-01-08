//package org.lyg.common.exceptions;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
//import javax.ws.rs.ext.ExceptionMapper;
//import javax.ws.rs.ext.Provider;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.lyg.common.constants.DetaDBConstant;
//@Provider
//@Slf4j
//public class RestExceptionMapper implements ExceptionMapper<Exception> {
//	private static Logger logger = LogManager.getLogger(RestExceptionMapper.class.getName());
//	@Override
//	public Response toResponse(Exception exception) {
//		logger.info("Catch Exception = " + exception);
//		if (exception instanceof DetaDBException) {
//			return Response.status(Status.OK).entity(exception).build();
//		}
//		DetaDBException error = new DetaDBException(DetaDBConstant.ERRORCODE_ER1001, ErrorCodeEnum.ER1001.getMsg());
//		return Response.status(Status.OK).entity(error).build();
//	}
//}
