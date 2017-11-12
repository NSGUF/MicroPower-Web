package com.mircolove;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.DataBase.DataBaseUtil;

public class MircoLoveDao {
	// 保存用户信息到数据库
	public boolean saveMircoLove(MircoLove mircoLove) {
		boolean flag = true;
		Connection conn = DataBaseUtil.getConnection();
		String sql = "insert into T_MIRCOLOVE_CHILDREN(mircolove_id,mircolove_target_amount,mircolove_raise_amount,mircolove_open_date,mircolove_divid_num,mircolove_list_title,mircolove_list_describe,mircolove_list_image,mircolove_list_min_image,mircolove_list_select,mircolove_list_addr,mircolove_list_support_time,mircolove_verify_state,user_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// new Date()为获取当前系统时间
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mircoLove.getMircolove_id());
			ps.setDouble(2, mircoLove.getMircolove_target_amount());
			ps.setDouble(3, mircoLove.getMircolove_raise_amount());
			ps.setString(4, mircoLove.getMircolove_open_date());
			ps.setInt(5, mircoLove.getMircolove_divid_num());
			ps.setString(6, mircoLove.getMircolove_list_title());
			ps.setString(7, mircoLove.getMircolove_list_describe());
			ps.setString(8, mircoLove.getMircolove_list_image());
			ps.setString(9, mircoLove.getMircolove_list_min_image());
			ps.setInt(10, mircoLove.getMircolove_list_select());
			ps.setString(11, mircoLove.getMircolove_list_addr());
			ps.setInt(12, mircoLove.getMircolove_list_support_time());
			ps.setInt(13, mircoLove.getMircolove_verify_state());
			ps.setString(14, mircoLove.getUser_id());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return flag;
	}

	// T_MIRCOLOVE中一共多少条信息
	public long listCount() {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select count(*) count1 from T_MIRCOLOVE_CHILDREN";
		long count = 0;
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				count = rs.getLong("count1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return count;
	}

	// T_MIRCOLOVE中一共多少条被选为精选的信息
	public long listSelectedCount() {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select count(*) count1 from T_MIRCOLOVE_CHILDREN where mircolove_list_select=1";
		long count = 0;
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				count = rs.getLong("count1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return count;
	}

	public ResultSet getSelectedMircoLove() {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_MIRCOLOVE_CHILDREN where list_select=1";
		ResultSet rs = null;
		try {
			Statement stm = conn.createStatement();
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return rs;
	}

	public MircoLove getMircoLove(String mircolove_id) {
		MircoLove mircolove = new MircoLove();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_MIRCOLOVE_CHILDREN where mircolove_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mircolove_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mircolove.setMircolove_id(mircolove_id);
				mircolove.setMircolove_target_amount(rs
						.getDouble("mircolove_target_amount"));
				mircolove.setMircolove_raise_amount(rs
						.getDouble("mircolove_raise_amount"));
				mircolove.setMircolove_open_date(rs
						.getString("mircolove_open_date"));
				mircolove.setMircolove_divid_num(rs
						.getInt("mircolove_divid_num"));
				mircolove.setMircolove_list_title(rs
						.getString("mircolove_list_title"));
				mircolove.setMircolove_list_describe(rs
						.getString("mircolove_list_describe"));
				mircolove.setMircolove_list_image(rs
						.getString("mircolove_list_image"));
				mircolove.setMircolove_list_min_image(rs
						.getString("mircolove_list_min_image"));
				mircolove.setMircolove_list_select(rs
						.getInt("mircolove_list_select"));
				mircolove.setMircolove_list_addr(rs
						.getString("mircolove_list_addr"));
				mircolove.setMircolove_list_support_time(rs
						.getInt("mircolove_list_support_time"));
				mircolove.setMircolove_verify_state(rs
						.getInt("mircolove_verify_state"));
				mircolove.setIs_delete(rs.getInt("is_delete"));
				mircolove.setUser_id(rs.getString("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return mircolove;
	}

	public boolean setMircoLoveDelete(String mircolove_id) {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "update T_MIRCOLOVE_CHILDREN set is_delete=1 where mircolove_id='"
				+ mircolove_id + "'";
		boolean flag = true;
		try {
			Statement stm = conn.createStatement();
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return flag;
	}

	public static List<MircoLove> getSelectMircoLoveList() {
		List<MircoLove> list = new ArrayList<MircoLove>();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_MIRCOLOVE_CHILDREN where is_delete=0 and mircolove_verify_state=3 and mircolove_list_select=1 and mircolove_verify_state=3";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				MircoLove mircolove = new MircoLove();
				mircolove.setMircolove_id(rs.getString("mircolove_id"));
				mircolove.setMircolove_target_amount(rs
						.getInt("mircolove_target_amount"));
				mircolove.setMircolove_raise_amount(rs
						.getInt("mircolove_raise_amount"));
				mircolove.setMircolove_open_date(rs
						.getString("mircolove_open_date"));
				mircolove.setMircolove_divid_num(rs
						.getInt("mircolove_divid_num"));
				mircolove.setMircolove_list_title(rs
						.getString("mircolove_list_title"));
				mircolove.setMircolove_list_describe(rs
						.getString("mircolove_list_describe"));
				mircolove.setMircolove_list_image(rs
						.getString("mircolove_list_image"));
				mircolove.setMircolove_list_min_image(rs
						.getString("mircolove_list_min_image"));
				mircolove.setMircolove_list_select(rs
						.getInt("mircolove_list_select"));
				mircolove.setMircolove_list_addr(rs
						.getString("mircolove_list_addr"));
				mircolove.setMircolove_list_support_time(rs
						.getInt("mircolove_list_support_time"));
				mircolove.setMircolove_verify_state(rs
						.getInt("mircolove_verify_state"));
				mircolove.setIs_delete(rs.getInt("is_delete"));
				mircolove.setUser_id(rs.getString("user_id"));
				list.add(mircolove);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return list;
	}

	public static List<MircoLove> getUnselectMircoLoveList() {
		List<MircoLove> list = new ArrayList<MircoLove>();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_MIRCOLOVE_CHILDREN where is_delete=0 and mircolove_verify_state=3 and mircolove_list_select=0";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				MircoLove mircolove = new MircoLove();
				mircolove.setMircolove_id(rs.getString("mircolove_id"));
				mircolove.setMircolove_target_amount(rs
						.getInt("mircolove_target_amount"));
				mircolove.setMircolove_raise_amount(rs
						.getInt("mircolove_raise_amount"));
				mircolove.setMircolove_open_date(rs
						.getString("mircolove_open_date"));
				mircolove.setMircolove_divid_num(rs
						.getInt("mircolove_divid_num"));
				mircolove.setMircolove_list_title(rs
						.getString("mircolove_list_title"));
				mircolove.setMircolove_list_describe(rs
						.getString("mircolove_list_describe"));
				mircolove.setMircolove_list_image(rs
						.getString("mircolove_list_image"));
				mircolove.setMircolove_list_min_image(rs
						.getString("mircolove_list_min_image"));
				mircolove.setMircolove_list_select(rs
						.getInt("mircolove_list_select"));
				mircolove.setMircolove_list_addr(rs
						.getString("mircolove_list_addr"));
				mircolove.setMircolove_list_support_time(rs
						.getInt("mircolove_list_support_time"));
				mircolove.setMircolove_verify_state(rs
						.getInt("mircolove_verify_state"));
				mircolove.setIs_delete(rs.getInt("is_delete"));
				mircolove.setUser_id(rs.getString("user_id"));
				list.add(mircolove);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return list;
	}

	public static List<MircoLove> getMircoLoveList() {
		List<MircoLove> list = new ArrayList<MircoLove>();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_MIRCOLOVE_CHILDREN where is_delete=0 and mircolove_verify_state=3";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				MircoLove mircolove = new MircoLove();
				mircolove.setMircolove_id(rs.getString("mircolove_id"));
				mircolove.setMircolove_target_amount(rs
						.getInt("mircolove_target_amount"));
				mircolove.setMircolove_raise_amount(rs
						.getInt("mircolove_raise_amount"));
				mircolove.setMircolove_open_date(rs
						.getString("mircolove_open_date"));
				mircolove.setMircolove_divid_num(rs
						.getInt("mircolove_divid_num"));
				mircolove.setMircolove_list_title(rs
						.getString("mircolove_list_title"));
				mircolove.setMircolove_list_describe(rs
						.getString("mircolove_list_describe"));
				mircolove.setMircolove_list_image(rs
						.getString("mircolove_list_image"));
				mircolove.setMircolove_list_min_image(rs
						.getString("mircolove_list_min_image"));
				mircolove.setMircolove_list_select(rs
						.getInt("mircolove_list_select"));
				mircolove.setMircolove_list_addr(rs
						.getString("mircolove_list_addr"));
				mircolove.setMircolove_list_support_time(rs
						.getInt("mircolove_list_support_time"));
				mircolove.setMircolove_verify_state(rs
						.getInt("mircolove_verify_state"));
				mircolove.setIs_delete(rs.getInt("is_delete"));
				mircolove.setUser_id(rs.getString("user_id"));
				list.add(mircolove);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return list;
	}

	public static String getTime(String open_date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now = null;
		java.util.Date date = null;
		try {
			now = df.parse(df.format(new java.util.Date()));
			date = df.parse(open_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long l = now.getTime() - date.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String spaceTime = "";
		if (day == 0) {
			if (hour == 0) {
				if (min == 0) {
					spaceTime = s + "秒前";
				} else {
					spaceTime = min + "分" + s + "秒前";
				}
			} else {
				spaceTime = hour + "小时" + min + "分前";
			}
		} else {
			if (hour == 0) {
				spaceTime = day + "天" + min + "分前";
			} else {
				spaceTime = day + "天" + hour + "小时前";
			}
		}
		return spaceTime;
	}

	public static List<MircoLove> getNotVirifyMircoLove() {
		List<MircoLove> list = new ArrayList<MircoLove>();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_MIRCOLOVE_CHILDREN where is_delete=0 and mircolove_verify_state=1";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				MircoLove mircolove = new MircoLove();
				mircolove.setMircolove_id(rs.getString("mircolove_id"));
				mircolove.setMircolove_target_amount(rs
						.getInt("mircolove_target_amount"));
				mircolove.setMircolove_raise_amount(rs
						.getInt("mircolove_raise_amount"));
				mircolove.setMircolove_open_date(rs
						.getString("mircolove_open_date"));
				mircolove.setMircolove_divid_num(rs
						.getInt("mircolove_divid_num"));
				mircolove.setMircolove_list_title(rs
						.getString("mircolove_list_title"));
				mircolove.setMircolove_list_describe(rs
						.getString("mircolove_list_describe"));
				mircolove.setMircolove_list_image(rs
						.getString("mircolove_list_image"));
				mircolove.setMircolove_list_min_image(rs
						.getString("mircolove_list_min_image"));
				mircolove.setMircolove_list_select(rs
						.getInt("mircolove_list_select"));
				mircolove.setMircolove_list_addr(rs
						.getString("mircolove_list_addr"));
				mircolove.setMircolove_list_support_time(rs
						.getInt("mircolove_list_support_time"));
				mircolove.setMircolove_verify_state(rs
						.getInt("mircolove_verify_state"));
				mircolove.setIs_delete(rs.getInt("is_delete"));
				mircolove.setUser_id(rs.getString("user_id"));
				list.add(mircolove);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return list;
	}

	public static boolean VirifyMircoLove(String id) {
		boolean flag = true;
		Connection conn = DataBaseUtil.getConnection();
		String sql = "update T_MIRCOLOVE_CHILDREN set mircolove_verify_state=3 where mircolove_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return flag;
	}

	public static boolean addSelectMircoLove(String id) {
		boolean flag = true;
		Connection conn = DataBaseUtil.getConnection();
		String sql = "update T_MIRCOLOVE_CHILDREN set mircolove_list_select=1 where mircolove_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return flag;
	}

	public static boolean UnvirifyMircoLove(String id) {
		boolean flag = true;
		Connection conn = DataBaseUtil.getConnection();
		String sql = "update T_MIRCOLOVE_CHILDREN set mircolove_verify_state=2 where mircolove_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return flag;
	}

	public static List<MircoLove> getMyMircoLoveList(String user_id) {
		List<MircoLove> list = new ArrayList<MircoLove>();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_MIRCOLOVE_CHILDREN where is_delete=0 and user_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MircoLove mircolove = new MircoLove();
				mircolove.setMircolove_id(rs.getString("mircolove_id"));
				mircolove.setMircolove_target_amount(rs
						.getInt("mircolove_target_amount"));
				mircolove.setMircolove_raise_amount(rs
						.getInt("mircolove_raise_amount"));
				mircolove.setMircolove_open_date(rs
						.getString("mircolove_open_date"));
				mircolove.setMircolove_divid_num(rs
						.getInt("mircolove_divid_num"));
				mircolove.setMircolove_list_title(rs
						.getString("mircolove_list_title"));
				mircolove.setMircolove_list_describe(rs
						.getString("mircolove_list_describe"));
				mircolove.setMircolove_list_image(rs
						.getString("mircolove_list_image"));
				mircolove.setMircolove_list_min_image(rs
						.getString("mircolove_list_min_image"));
				mircolove.setMircolove_list_select(rs
						.getInt("mircolove_list_select"));
				mircolove.setMircolove_list_addr(rs
						.getString("mircolove_list_addr"));
				mircolove.setMircolove_list_support_time(rs
						.getInt("mircolove_list_support_time"));
				mircolove.setMircolove_verify_state(rs
						.getInt("mircolove_verify_state"));
				mircolove.setIs_delete(rs.getInt("is_delete"));
				mircolove.setUser_id(rs.getString("user_id"));
				list.add(mircolove);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return list;
	}
}
