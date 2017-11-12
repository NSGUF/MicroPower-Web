package com.donation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.DataBase.DataBaseUtil;
import com.mircolove.MircoLove;

public class DonationInfoDao {
	// 保存用户信息到数据库
	public boolean saveDonation(DonationInfo donationInfo) {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "insert into T_DONATIONINFO(donation_id,donation_raise_goods,donation_trans_cost,donation_close_date,donation_open_date,donation_title,donation_describe,donation_image,donation_min_image,donation_select_need_or_dona,is_donation_black,is_success,user_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// new Date()为获取当前系统时间
		boolean flag = true;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, donationInfo.getDonation_id());
			ps.setString(2, donationInfo.getDonation_raise_goods());
			ps.setInt(3, donationInfo.getDonation_trans_cost());
			ps.setString(4, donationInfo.getDonation_close_date());
			ps.setString(5, donationInfo.getDonation_open_date());
			ps.setString(6, donationInfo.getDonation_title());
			ps.setString(7, donationInfo.getDonation_describe());
			ps.setString(8, donationInfo.getDonation_image());
			ps.setString(9, donationInfo.getDonation_min_image());
			ps.setInt(10, donationInfo.getDonation_select_need_or_dona());
			ps.setInt(11, donationInfo.getIs_donation_black());
			ps.setInt(12, donationInfo.getIs_success());
			ps.setString(13, donationInfo.getUser_id());
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
		String sql = "select count(*) count1 from T_DONATIONINFO";
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

	public DonationInfo getDonationInfo(String donation_id) {
		DonationInfo donationInfo = new DonationInfo();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_DONATIONINFO where donation_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, donation_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				donationInfo.setDonation_id(donation_id);
				donationInfo.setDonation_raise_goods(rs
						.getString("donation_raise_goods"));
				donationInfo.setDonation_trans_cost(rs
						.getInt("donation_trans_cost"));
				donationInfo.setDonation_close_date(rs
						.getString("donation_close_date"));
				donationInfo.setDonation_open_date(rs
						.getString("donation_open_date"));
				donationInfo.setDonation_title(rs.getString("donation_title"));
				donationInfo.setDonation_describe(rs
						.getString("donation_describe"));
				donationInfo.setDonation_image(rs.getString("donation_image"));
				donationInfo.setDonation_min_image(rs
						.getString("donation_min_image"));
				donationInfo.setDonation_select_need_or_dona(rs
						.getInt("donation_select_need_or_dona"));
				donationInfo.setIs_donation_black(rs
						.getInt("is_donation_black"));
				donationInfo.setUser_id(rs.getString("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return donationInfo;
	}

	public int getDonationInfoContentRank(String donation_id) {
		int donationinfo_comment_rank = 0;
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select donationinfo_comment_rank from T_DONATIONINFO_COMMENT where donation_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, donation_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				donationinfo_comment_rank = rs
						.getInt("donationinfo_comment_rank");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return donationinfo_comment_rank;
	}

	public static List<DonationInfo> getDonationList() {
		List<DonationInfo> list = new ArrayList<DonationInfo>();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_DONATIONINFO where is_delete=0 and is_donation_black=0 and donation_verify_state=3 and is_success=0";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				DonationInfo donationInfo = new DonationInfo();
				donationInfo.setDonation_close_date(rs
						.getString("donation_close_date"));
				donationInfo.setDonation_describe(rs
						.getString("donation_describe"));
				donationInfo.setDonation_image(rs.getString("donation_image"));
				donationInfo.setDonation_min_image(rs
						.getString("donation_min_image"));
				donationInfo.setDonation_id(rs.getString("donation_id"));
				donationInfo.setDonation_open_date(rs
						.getString("donation_open_date"));
				donationInfo.setIs_delete(rs.getInt("is_delete"));
				donationInfo.setDonation_raise_goods(rs
						.getString("donation_raise_goods"));
				donationInfo.setDonation_select_need_or_dona(rs
						.getInt("donation_select_need_or_dona"));
				donationInfo.setDonation_title(rs.getString("donation_title"));
				donationInfo.setDonation_trans_cost(rs
						.getInt("donation_trans_cost"));
				donationInfo.setIs_donation_black(rs
						.getInt("is_donation_black"));
				donationInfo.setIs_success(rs.getInt("is_success"));
				donationInfo.setUser_id(rs.getString("user_id"));
				donationInfo.setDonation_verify_state(rs
						.getInt("donation_verify_state"));
				donationInfo.setDonation_addr(rs.getString("donation_addr"));
				list.add(donationInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return list;
	}

	public static List<DonationInfo> getNotVirifyDonation() {
		List<DonationInfo> list = new ArrayList<DonationInfo>();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_DONATIONINFO where is_delete=0  and donation_verify_state=1";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				DonationInfo donationInfo = new DonationInfo();
				donationInfo.setDonation_close_date(rs
						.getString("donation_close_date"));
				donationInfo.setDonation_describe(rs
						.getString("donation_describe"));
				donationInfo.setDonation_image(rs.getString("donation_image"));
				donationInfo.setDonation_min_image(rs
						.getString("donation_min_image"));
				donationInfo.setDonation_id(rs.getString("donation_id"));
				donationInfo.setDonation_open_date(rs
						.getString("donation_open_date"));
				donationInfo.setIs_delete(rs.getInt("is_delete"));
				donationInfo.setDonation_raise_goods(rs
						.getString("donation_raise_goods"));
				donationInfo.setDonation_select_need_or_dona(rs
						.getInt("donation_select_need_or_dona"));
				donationInfo.setDonation_title(rs.getString("donation_title"));
				donationInfo.setDonation_trans_cost(rs
						.getInt("donation_trans_cost"));
				donationInfo.setIs_donation_black(rs
						.getInt("is_donation_black"));
				donationInfo.setIs_success(rs.getInt("is_success"));
				donationInfo.setUser_id(rs.getString("user_id"));
				donationInfo.setDonation_verify_state(rs
						.getInt("donation_verify_state"));
				donationInfo.setDonation_addr(rs.getString("donation_addr"));
				list.add(donationInfo);
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
		String sql = "update T_DONATIONINFO set donation_verify_state=3 where donation_id=?";
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
		String sql = "update T_DONATIONINFO set donation_verify_state=2 where donation_id=?";
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

	public boolean setDonationInfoDelete(String donation_id) {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "update T_DONATIONINFO set is_delete=1 where donation_id='"
				+ donation_id + "'";
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

	
	public static List<DonationInfo> getMyDonationList(String user_id) {
		List<DonationInfo> list = new ArrayList<DonationInfo>();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_DONATIONINFO where is_delete=0 and user_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DonationInfo donationInfo = new DonationInfo();
				donationInfo.setDonation_close_date(rs
						.getString("donation_close_date"));
				donationInfo.setDonation_describe(rs
						.getString("donation_describe"));
				donationInfo.setDonation_image(rs.getString("donation_image"));
				donationInfo.setDonation_min_image(rs
						.getString("donation_min_image"));
				donationInfo.setDonation_id(rs.getString("donation_id"));
				donationInfo.setDonation_open_date(rs
						.getString("donation_open_date"));
				donationInfo.setIs_delete(rs.getInt("is_delete"));
				donationInfo.setDonation_raise_goods(rs
						.getString("donation_raise_goods"));
				donationInfo.setDonation_select_need_or_dona(rs
						.getInt("donation_select_need_or_dona"));
				donationInfo.setDonation_title(rs.getString("donation_title"));
				donationInfo.setDonation_trans_cost(rs
						.getInt("donation_trans_cost"));
				donationInfo.setIs_donation_black(rs
						.getInt("is_donation_black"));
				donationInfo.setIs_success(rs.getInt("is_success"));
				donationInfo.setUser_id(rs.getString("user_id"));
				donationInfo.setDonation_verify_state(rs
						.getInt("donation_verify_state"));
				donationInfo.setDonation_addr(rs.getString("donation_addr"));
				list.add(donationInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return list;
	}
}
