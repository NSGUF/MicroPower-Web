package com.volunteer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.DataBase.DataBaseUtil;

public class VolunteerDao {
	// 保存用户信息到数据库
	public static boolean saveVolunteer(Volunteer volunteer, String user_id) {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "update T_USERINFO set wallet_id=?,is_volunteer=0,verify_info=?,verify_image=?,ID_card=?,verify_state=4 where user_id=?";
		// new Date()为获取当前系统时间
		boolean flag = true;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, volunteer.getWallet_id());
			ps.setString(2, volunteer.getVerify_info());
			ps.setString(3, volunteer.getVerify_image());
			ps.setString(4, volunteer.getID_card());
			ps.setString(5, user_id);
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

	// 判断用户是否通过审核
	public static int userIsExist(String user_id) {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		int result = 2;
		String sql = "select * from T_USERINFO where user_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("verify_state");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return result;
	}

	public static boolean virifyVolunteer(String id) {
		boolean flag = true;
		Connection conn = DataBaseUtil.getConnection();
		String sql = "update T_USERINFO set verify_state=3 where user_id=?";
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

	public static boolean unirifyVolunteer(String id) {
		boolean flag = true;
		Connection conn = DataBaseUtil.getConnection();
		String sql = "update T_USERINFO set verify_state=2 where user_id=?";
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
}
