package com.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.DataBase.DataBaseUtil;

public class CommentContentDao {
	// 保存用户信息到数据库
	public void saveComment(CommentContent commentContent) {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "insert into T_MIRCOLOVE_CHILDREN_COMMENT(mircolove_comment_id ,mircolove_comment_rank,mircolove_id) values(?,?,?)";
		// new Date()为获取当前系统时间
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, commentContent.getMircolove_comment_id());
			ps.setInt(2, commentContent.getMircolove_comment_rank());
			ps.setString(3, commentContent.getMircolove_id());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
	}

	public void saveCntent(CommentContent commentContent) {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "insert into T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT(mircolove_comment_content_id,from_user_id,to_user_id,mircolove_comment_content,mircolove_comment_content_time,mircolove_comment_content_rank,mircolove_comment_id) values(?,?,?,?,?,?,?)";
		// new Date()为获取当前系统时间
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, commentContent.getMircolove_comment_content_id());
			ps.setString(2, commentContent.getFrom_user_id());
			ps.setString(3, commentContent.getTo_user_id());
			ps.setString(4, commentContent.getMircolove_comment_content());
			ps.setString(5, commentContent.getMircolove_comment_content_time());
			ps.setInt(6, commentContent.getMircolove_comment_content_rank());
			ps.setString(7, commentContent.getMircolove_comment_id());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
	}

	public long listCommentCount() {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select count(*) count1 from T_MIRCOLOVE_CHILDREN_COMMENT";
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

	public long listContentCount() {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select count(*) count1 from T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT";
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

	public long listContentCount(String mircolove_comment_id) {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select count(*) count1 from T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT where mircolove_comment_id='"
				+ mircolove_comment_id + "'";
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

	public CommentContent getDonationInfo(String mircolove_comment_content_id) {
		CommentContent commentContent = new CommentContent();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_MIRCOLOVE_CHILDREN_COMMENT,T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT where T_MIRCOLOVE_CHILDREN_COMMENT.mircolove_comment_id=T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT.mircolove_comment_id and mircolove_comment_content_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mircolove_comment_content_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				commentContent.setFrom_user_id(rs.getString("from_user_id"));
				commentContent.setMircolove_comment_content(rs
						.getString("mircolove_comment_content"));
				commentContent.setMircolove_comment_content_id(rs
						.getString("mircolove_comment_content_id"));
				commentContent.setMircolove_comment_content_rank(rs
						.getInt("mircolove_comment_content_rank"));
				commentContent.setMircolove_comment_content_time(rs
						.getString("mircolove_comment_content_time"));
				commentContent.setMircolove_comment_id(rs
						.getString("mircolove_comment_id"));
				commentContent.setMircolove_comment_rank(rs
						.getInt("mircolove_comment_rank"));
				commentContent.setMircolove_id(rs.getString("mircolove_id"));
				commentContent.setTo_user_id(rs.getString("to_user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return commentContent;
	}

	// 保存用户信息到数据库
	public void saveDonationInfoComment(CommentContent commentContent) {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "insert into T_DONATIONINFO_COMMENT(donationinfo_comment_id ,donationinfo_comment_rank,donation_id) values(?,?,?)";
		// new Date()为获取当前系统时间
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, commentContent.getMircolove_comment_id());
			ps.setInt(2, commentContent.getMircolove_comment_rank());
			ps.setString(3, commentContent.getMircolove_id());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
	}

	public void saveDonationInfoCntent(CommentContent commentContent) {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "insert into T_DONATIONINFO_COMMENT_CONTENT(donationinfo_comment_content_id,from_user_id,to_user_id,donationinfo_comment_content,donationinfo_comment_content_time,donationinfo_comment_content_rank,donationinfo_comment_id) values(?,?,?,?,?,?,?)";
		// new Date()为获取当前系统时间
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, commentContent.getMircolove_comment_content_id());
			ps.setString(2, commentContent.getFrom_user_id());
			ps.setString(3, commentContent.getTo_user_id());
			ps.setString(4, commentContent.getMircolove_comment_content());
			ps.setString(5, commentContent.getMircolove_comment_content_time());
			ps.setInt(6, commentContent.getMircolove_comment_content_rank());
			ps.setString(7, commentContent.getMircolove_comment_id());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
	}

	public int listDCCount(String donation_id) {
		// 获取数据库连接connection对象
		int donationinfo_comment_rank = 0;
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select donationinfo_comment_rank from T_DONATIONINFO_COMMENT where donation_id="
				+ "'" + donation_id + "'";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				donationinfo_comment_rank = rs
						.getInt("donationinfo_comment_rank");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return donationinfo_comment_rank + 1;
	}

	public long listDCCommentCount() {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select count(*) count1 from T_DONATIONINFO_COMMENT_CONTENT";
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

	public long listWCCommentCount() {
		// 获取数据库连接connection对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select count(*) count1 from T_WITNESSINFO_COMMENT_CONTENT";
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

	public int listWCCount(String witness_id) {
		// 获取数据库连接connection对象
		int donationinfo_comment_rank = 0;
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select witnessinfo_comment_rank from T_WITNESSINFO_COMMENT where witness_id="
				+ "'" + witness_id + "'";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				donationinfo_comment_rank = rs
						.getInt("witnessinfo_comment_rank");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return donationinfo_comment_rank + 1;
	}

	public void saveWitnessComment(CommentContent commentContent) {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "insert into T_WITNESSINFO_COMMENT(witnessinfo_comment_id ,witnessinfo_comment_rank,witness_id) values(?,?,?)";
		// new Date()为获取当前系统时间
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, commentContent.getMircolove_comment_id());
			ps.setInt(2, commentContent.getMircolove_comment_rank());
			ps.setString(3, commentContent.getMircolove_id());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
	}

	public void saveWitnessCntent(CommentContent commentContent) {
		Connection conn = DataBaseUtil.getConnection();
		String sql = "insert into T_WITNESSINFO_COMMENT_CONTENT(witnessinfo_comment_content_id,from_user_id,to_user_id,witnessinfo_comment_content,witnessinfo_comment_time,witnessinfo_comment_content_rank,witnessinfo_comment_id) values(?,?,?,?,?,?,?)";
		// new Date()为获取当前系统时间
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, commentContent.getMircolove_comment_content_id());
			ps.setString(2, commentContent.getFrom_user_id());
			ps.setString(3, commentContent.getTo_user_id());
			ps.setString(4, commentContent.getMircolove_comment_content());
			ps.setString(5, commentContent.getMircolove_comment_content_time());
			ps.setInt(6, commentContent.getMircolove_comment_content_rank());
			ps.setString(7, commentContent.getMircolove_comment_id());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
	}
}
