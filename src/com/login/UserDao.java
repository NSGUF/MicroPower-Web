package com.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.DataBase.DataBaseUtil;

public class UserDao {
	// 判断用户是否存在 判断的是电话号码
	public boolean userIsExist(String cell_phone_num) {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_USERINFO where cell_phone_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cell_phone_num);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				return true;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return false;
	}

	public boolean bgUserIsExist(String userName, String userPwd) {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_BG where user_name=?  and user_pwd=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, userPwd);
			ResultSet rs = ps.executeQuery();
			while (!rs.next()) {
				return false;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return true;
	}

	// 总共有多少用户，用于创建用户id
	public long userCount() {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select count(*) count1 from T_USERINFO";
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

	// 保存用户信息到数据库
	public void saveUser(User user) {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "insert into T_USERINFO(user_id,head_portrait,pet_name,cell_phone_id,wallet_id,is_volunteer,is_black) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUser_id());
			ps.setString(2, user.getHead_portrait());
			ps.setString(3, user.getPet_name());
			ps.setString(4, user.getCell_phone_id());
			ps.setString(5, user.getWallet_id());
			ps.setInt(6, user.getIs_volunteer());
			ps.setInt(7, user.getIs_black());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
	}

	// 登录
	public User login(String cell_phone_id) {
		User user = null;
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_USERINFO where cell_phone_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cell_phone_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUser_id(rs.getString("user_id"));
				user.setCell_phone_id(rs.getString("cell_phone_id"));
				user.setHead_portrait(rs.getString("head_portrait"));
				user.setAlipay_id(rs.getString("alipay_id"));
				user.setMicro_blog_id(rs.getString("micro_blog_id"));
				user.setPet_name(rs.getString("pet_name"));
				user.setWechat_id(rs.getString("wechat_id"));
				user.setQq_id(rs.getString("qq_id"));
				user.setWallet_id(rs.getString("wallet_id"));
				user.setIs_volunteer(rs.getInt("is_volunteer"));
				user.setVerify_info(rs.getString("verify_info"));
				user.setVerify_state(rs.getInt("verify_state"));
				user.setIs_black(rs.getInt("is_black"));
				user.setOpen_date(rs.getString("open_date"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return user;
	}

	public User getUser(String user_id) {
		User user = null;
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_USERINFO where user_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUser_id(rs.getString("user_id"));
				user.setCell_phone_id(rs.getString("cell_phone_id"));
				user.setHead_portrait(rs.getString("head_portrait"));
				user.setAlipay_id(rs.getString("alipay_id"));
				user.setMicro_blog_id(rs.getString("micro_blog_id"));
				user.setPet_name(rs.getString("pet_name"));
				user.setWechat_id(rs.getString("wechat_id"));
				user.setQq_id(rs.getString("qq_id"));
				user.setWallet_id(rs.getString("wallet_id"));
				user.setIs_volunteer(rs.getInt("is_volunteer"));
				user.setVerify_info(rs.getString("verify_info"));
				user.setVerify_state(rs.getInt("verify_state"));
				user.setIs_black(rs.getInt("is_black"));
				user.setOpen_date(rs.getString("open_date"));
				user.setID_card(rs.getString("ID_card"));
				user.setVerify_image(rs.getString("verify_image"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return user;
	}

	public static List<User> getVolunteer() {
		List<User> users = new ArrayList<User>();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_USERINFO where verify_state=4";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				User user = new User();
				user.setUser_id(rs.getString("user_id"));
				user.setCell_phone_id(rs.getString("cell_phone_id"));
				user.setHead_portrait(rs.getString("head_portrait"));
				user.setAlipay_id(rs.getString("alipay_id"));
				user.setMicro_blog_id(rs.getString("micro_blog_id"));
				user.setPet_name(rs.getString("pet_name"));
				user.setWechat_id(rs.getString("wechat_id"));
				user.setQq_id(rs.getString("qq_id"));
				user.setWallet_id(rs.getString("wallet_id"));
				user.setIs_volunteer(rs.getInt("is_volunteer"));
				user.setVerify_info(rs.getString("verify_info"));
				user.setVerify_state(rs.getInt("verify_state"));
				user.setIs_black(rs.getInt("is_black"));
				user.setOpen_date(rs.getString("open_date"));
				user.setID_card(rs.getString("ID_card"));
				user.setVerify_image(rs.getString("verify_image"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return users;
	}

	public User modifyUser(User user, String pet_name, String head_portrait) {
		Connection conn = DataBaseUtil.getConnection();
		user.setPet_name(pet_name);
		String sql = "";
		if (head_portrait.equals("")) {
			sql = "update T_USERINFO set pet_name=? where user_id=?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, pet_name);
				ps.setString(2, user.getUser_id());
				ps.executeUpdate();
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DataBaseUtil.closeConnection(conn);
			}
		} else {
			user.setHead_portrait(head_portrait);
			sql = "update T_USERINFO set pet_name=?,head_portrait=? where user_id=?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, pet_name);
				ps.setString(2, head_portrait);
				ps.setString(3, user.getUser_id());
				ps.executeUpdate();
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DataBaseUtil.closeConnection(conn);
			}
		}

		return user;
	}
}
