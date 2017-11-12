package com.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.DataBase.DataBaseUtil;
import com.mircolove.MircoLove;

public class AddressDao {
	public boolean addAddress(Address address, String user_id) {
		boolean flag = true;
		Connection conn = DataBaseUtil.getConnection();
		String sql = "insert into T_RECIPIENTINFO(rece_name,rece_cell_id,detailed_addr,user_id) values(?,?,?,?)";
		// new Date()为获取当前系统时间
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, address.getName());
			ps.setString(2, address.getCellphone());
			ps.setString(3, address.getProvince() + " " + address.getCity()
					+ " " + address.getCounty() + " " + address.getDetail());
			ps.setString(4, user_id);
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

	public static List<Address> getListAddress(String user_id) {
		List<Address> list = new ArrayList<Address>();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_RECIPIENTINFO where user_id='" + user_id
				+ "' and is_delete=0";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String[] detail = rs.getString("detailed_addr").split(" ");
				Address a = new Address(rs.getString("rece_name"),
						rs.getString("rece_cell_id"), detail[0], detail[1],
						detail[2], detail[3], rs.getString("is_default"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(conn);
		}
		return list;
	}
}
