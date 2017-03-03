package com.mircolove;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.DataBase.DataBaseUtil;

public class DoMircoLoveDao {
	// 保存用户信息到数据库
	public void saveDoMircoLove(DoMircoLove doMircoLove) {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "insert into T_DO_MIRCOLOVE_CHILDREN(do_mircolove_id,do_mircolove_time,do_mircolove_amount ,user_id,mircolove_id,mircolove_comment_id) values(?,?,?,?,?,?)";
		// new Date()为获取当前系统时间
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, doMircoLove.getDo_mircolove_id());
			ps.setString(2, doMircoLove.getDo_mircolove_time());
			ps.setDouble(3, doMircoLove.getDo_mircolove_amount());
			ps.setString(4, doMircoLove.getUser_id());
			ps.setString(5, doMircoLove.getMircolove_id());
			ps.setString(6, doMircoLove.getMircolove_comment_id());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
	}

	// T_MIRCOLOVE中一共多少条信息
	public long listCount() {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select count(*) count1 from T_DO_MIRCOLOVE_CHILDREN";
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

	// T_MIRCOLOVE中一共多少条信息
	public long listNowCount(String mircolove_id) {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select count(*) count1 from T_DO_MIRCOLOVE_CHILDREN where mircolove_id="
				+ "'" + mircolove_id + "'";
		System.out.println(sql);
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

	public DoMircoLove getDonationInfo(String do_mircolove_id) {
		DoMircoLove doMircoLove = new DoMircoLove();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_DO_MIRCOLOVE_CHILDREN where do_mircolove_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, do_mircolove_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				doMircoLove.setDo_mircolove_id(do_mircolove_id);
				doMircoLove.setDo_mircolove_amount(rs
						.getDouble("do_mircolove_amount"));
				doMircoLove.setDo_mircolove_time(rs
						.getString("do_mircolove_time"));
				doMircoLove.setMircolove_comment_id(rs
						.getString("mircolove_comment_id"));
				doMircoLove.setMircolove_id(rs.getString("mircolove_id"));
				doMircoLove.setUser_id(rs.getString("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return doMircoLove;
	}

	public void setMircoloveRaiseAmount(String do_mircolove_amount,
			String mircolove_id) {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "update T_MIRCOLOVE_CHILDREN set mircolove_raise_amount=mircolove_raise_amount+? where mircolove_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, do_mircolove_amount);
			ps.setString(2, mircolove_id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
	}

	public void setMircoloveSupportNum(String mircolove_id) {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "update T_MIRCOLOVE_CHILDREN set mircolove_list_support_time=mircolove_list_support_time+1 where mircolove_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mircolove_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
	}

}
