package LoginTask.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

import LoginTask.model.Joiner;
import LoginTask.model.ConvertJoiner;

public class JdbcTemplateJoinerDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Joiner selectOne(String id) {

		Joiner resultObj = null;
		String sql = "select * from member where id= ?";

		// 0.query 메서드 이용
		List<Joiner> result = jdbcTemplate.query(sql, new JoinerRowMapper(), id);
		resultObj = result.isEmpty() ? null : result.get(0);

		// 1.queryForObject 메서드 이용
		/*
		 * try { resultObj = jdbcTemplate.queryForObject(sql, new JoinerRowMapper(),
		 * id); }catch(EmptyResultDataAccessException e){ return resultObj; }
		 */

		return resultObj;
	}

	// 회원가입
	public int insert(Joiner joiner) {

		int resultCnt = 0;

		String sql = "insert into member values(?,?,?,?,?,?,?,?)";
		String birth = joiner.getBirth_year() + "-" + joiner.getBirth_mon() + "-" + joiner.getBirth_day();

		resultCnt = jdbcTemplate.update(sql, joiner.getId(), joiner.getPwd(), joiner.getName(), birth,
				joiner.getGender(), joiner.getEmail(), joiner.getPhone(), joiner.getPhoto());

		return resultCnt;
	}

	// 멤버리스트
	public int selectCount() {

		int resultCnt = 0;
		String sql = "select count(*) from member";

		resultCnt = jdbcTemplate.queryForObject(sql, Integer.class);

		return resultCnt;
	}

	public List<Joiner> selectList(int firstRow, int endRow) {

		List<Joiner> memberList = null;
		String sql = "select * from member order by id desc limit ?,?";
/*		String sql = "select id, pwd, name, birth, gender, email, phone, photo "
				+ "from (select rownum rnum, id, pwd, name, birth, gender, email, phone, photo "
				+ "      from(select *  " + "           from member m " + "           order by m.name desc) "
				+ "       where rownum <= ?) " + "where rnum >= ?";*/

		memberList = jdbcTemplate.query(sql, new JoinerRowMapper(),firstRow, endRow);

		return memberList;

	}

	// 삭제
	public boolean delete(String id) {

		boolean result = false;
		String sql = "delete from member where id = ?";

		if (jdbcTemplate.update(sql, id) == 1) {
			result = true;
		}
		return result;

	}

	// JSON
	public List<Joiner> selectAll() {
		List<Joiner> memberList = null;
		String sql = "select * from member";

		memberList = jdbcTemplate.query(sql, new JoinerRowMapper());

		return memberList;

	}

	public List<ConvertJoiner> convertedSelectAll() {
		List<ConvertJoiner> memberList = null;
		String sql = "select * from member";

		memberList = jdbcTemplate.query(sql, new ConvertJoinerRowMapper());

		return memberList;

	}

	public List<ConvertJoiner> convertedListbyBirth(String from, String to) throws ParseException{
		List<ConvertJoiner> memberList = null;
	
		String sql = "select * from member where birth between '" + from + "' and '" + to +"'" ;

		memberList = jdbcTemplate.query(sql, new ConvertJoinerRowMapper());

		return memberList;
	}

}
