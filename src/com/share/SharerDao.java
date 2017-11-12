package com.share;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.DataBase.DataBaseUtil;
import com.donation.DonationInfo;
import com.mircolove.MircoLoveDao;

public class SharerDao {
	// 保存用户信息到数据库
	public boolean saveSharer(Sharer sharer) {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "insert into T_WITNESSINFO(witness_id,witness_title,witness_describe,witness_image,witness_min_image,witness_verify_state,user_id,witness_open_date) values(?,?,?,?,?,?,?,?)";
		// new Date()为获取当前系统时间
		boolean flag = true;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sharer.getWitness_id());
			ps.setString(2, sharer.getWitness_title());
			ps.setString(3, sharer.getWitness_describe());
			ps.setString(4, sharer.getWitness_image());
			ps.setString(5, sharer.getWitness_min_image());
			ps.setInt(6, sharer.getWitness_verify_state());
			ps.setString(7, sharer.getUser_id());
			ps.setString(8, sharer.getWitness_open_date());
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
		String sql = "select count(*) count1 from T_WITNESSINFO";
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

	public Sharer getSharer(String witness_id) {
		Sharer sharer = new Sharer();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_WITNESSINFO where witness_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, witness_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sharer.setWitness_id(witness_id);
				sharer.setWitness_title(rs.getString("witness_title"));
				sharer.setWitness_open_date(rs.getString("witness_open_date"));
				sharer.setWitness_describe(rs.getString("witness_describe"));
				sharer.setWitness_image(rs.getString("witness_image"));
				sharer.setWitness_min_image(rs.getString("witness_min_image"));
				sharer.setWitness_verify_state(rs
						.getInt("witness_verify_state"));
				sharer.setUser_id(rs.getString("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return sharer;
	}

	public boolean setShareDelete(String witness_id) {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "update T_WITNESSINFO set is_delete=1 where witness_id='"
				+ witness_id + "'";
		boolean falg = true;
		System.out.print(sql);
		try {
			Statement stm = conn.createStatement();
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			falg = false;
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return falg;
	}

	public static List<Sharer> getSharerList() {
		List<Sharer> list = new ArrayList<Sharer>();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_WITNESSINFO where is_delete=0 and is_witness_black=0 and witness_verify_state=3";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Sharer sharer = new Sharer();
				sharer.setUser_id(rs.getString("user_id"));
				sharer.setWitness_describe(rs.getString("witness_describe"));
				sharer.setWitness_id(rs.getString("witness_id"));
				sharer.setWitness_image(rs.getString("witness_image"));
				sharer.setWitness_min_image(rs.getString("witness_min_image"));
				sharer.setWitness_open_date(MircoLoveDao.getTime(rs
						.getString("witness_open_date")));
				sharer.setIs_witness_black(rs.getInt("is_witness_black"));
				sharer.setWitness_title(rs.getString("witness_title"));
				sharer.setWitness_verify_state(rs
						.getInt("witness_verify_state"));
				sharer.setIs_delete(rs.getInt("is_delete"));
				sharer.setWitness_addr(rs.getString("witness_addr"));
				list.add(sharer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return list;
	}

	public static List<Sharer> getNotVirifySharer() {
		List<Sharer> list = new ArrayList<Sharer>();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_WITNESSINFO where is_delete=0 and witness_verify_state=1";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Sharer sharer = new Sharer();
				sharer.setUser_id(rs.getString("user_id"));
				sharer.setWitness_describe(rs.getString("witness_describe"));
				sharer.setWitness_id(rs.getString("witness_id"));
				sharer.setWitness_image(rs.getString("witness_image"));
				sharer.setWitness_min_image(rs.getString("witness_min_image"));
				sharer.setWitness_open_date(MircoLoveDao.getTime(rs
						.getString("witness_open_date")));
				sharer.setIs_witness_black(rs.getInt("is_witness_black"));
				sharer.setWitness_title(rs.getString("witness_title"));
				sharer.setWitness_verify_state(rs
						.getInt("witness_verify_state"));
				sharer.setIs_delete(rs.getInt("is_delete"));
				sharer.setWitness_addr(rs.getString("witness_addr"));
				list.add(sharer);
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
		String sql = "update T_WITNESSINFO set witness_verify_state=3 where witness_id=?";
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
		String sql = "update T_WITNESSINFO set witness_verify_state=2 where witness_id=?";
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

	public static List<Sharer> getMySharerList(String user_id) {
		List<Sharer> list = new ArrayList<Sharer>();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_WITNESSINFO where is_delete=0 and user_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sharer sharer = new Sharer();
				sharer.setUser_id(rs.getString("user_id"));
				sharer.setWitness_describe(rs.getString("witness_describe"));
				sharer.setWitness_id(rs.getString("witness_id"));
				sharer.setWitness_image(rs.getString("witness_image"));
				sharer.setWitness_min_image(rs.getString("witness_min_image"));
				sharer.setWitness_open_date(MircoLoveDao.getTime(rs
						.getString("witness_open_date")));
				sharer.setIs_witness_black(rs.getInt("is_witness_black"));
				sharer.setWitness_title(rs.getString("witness_title"));
				sharer.setWitness_verify_state(rs
						.getInt("witness_verify_state"));
				sharer.setIs_delete(rs.getInt("is_delete"));
				sharer.setWitness_addr(rs.getString("witness_addr"));
				list.add(sharer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return list;
	}

}
