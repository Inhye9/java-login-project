package LoginTask.messageService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import LoginTask.dao.JdbcTemplateMessageDao;
import LoginTask.dao.JoinerInterfaceDao;
import LoginTask.dao.MessageDao;
import LoginTask.model.Message;
import LoginTask.model.MessageListView;
import LoginTask.service.ServiceException;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MessageListService {
	
/*	@Autowired
	MessageDao dao;*/
	
	/*@Autowired
	JdbcTemplateMessageDao dao;*/
	
	@Autowired
	SqlSessionTemplate sqlsessionTemplate;
	JoinerInterfaceDao dao;

	//한 페이지당 보여줄 방명록 수
	private static final int MESSAGE_COUNT_PER_PAGE = 5;
	
	//페이지 넘버를 클릭하면, 해당 리스트들(10개씩) 가져오는 메서드
	public MessageListView getMessageList(int pageNumber) throws ServiceException{
		
		dao = sqlsessionTemplate.getMapper(JoinerInterfaceDao.class);
	/*	Connection conn = null;*/
		MessageListView messageListView = null;
		
		//현재 페이지 넘버 구하기
		int currentPageNumber = 1; 

		if(pageNumber > 0) {
			currentPageNumber = pageNumber;
		}
		
		//본격 메서드 구하기 
	/*	try {*/
			/*conn = ConnectionProvider.getConnection();*/
			
			//전체 게시물의 수
			/*int messageTotalCount = dao.selectCount(conn);*/
			int messageTotalCount = dao.selectMessageCount();
			
			List<Message> messageList = null;
			int firstRow = 0;
			int endRow = 0;
			
			if(messageTotalCount>0) {
				
				firstRow = (currentPageNumber - 1)*MESSAGE_COUNT_PER_PAGE;
				endRow = firstRow+ MESSAGE_COUNT_PER_PAGE - 1 ;
				/*messageList = dao.selectList(conn,firstRow, endRow);*/
				messageList = dao.selectMessageList(firstRow, endRow);
				
			}else {
				currentPageNumber = 0;
				messageList = Collections.emptyList();
			}
			
			messageListView = new MessageListView(
					messageList, 
					messageTotalCount, 
					currentPageNumber, 
					MESSAGE_COUNT_PER_PAGE, 
					firstRow, 
					endRow);
			
			return messageListView;
			
		/*} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		
		return messageListView;*/
	}

}
