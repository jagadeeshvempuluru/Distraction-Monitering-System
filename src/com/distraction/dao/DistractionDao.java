package com.distraction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.distraction.model.Distraction;
import com.distraction.util.DBConnection;

public class DistractionDao {

// ADD DISTRACTION
public boolean addDistraction(Distraction d) {

    String sql =
        "INSERT INTO distraction(student_id, type, minutes, date) VALUES (?, ?, ?, CURDATE())";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, d.getStudentId());
        ps.setString(2, d.getType());
        ps.setInt(3, d.getMinutes());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}

// TOTAL MINUTES
public int getTotalDistractionTime(int studentId) {

    String sql =
        "SELECT SUM(minutes) FROM distraction WHERE student_id=?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, studentId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}

// TODAY MINUTES
public int getTodayDistraction(int studentId) {

    String sql =
        "SELECT SUM(minutes) FROM distraction WHERE student_id=? AND date=CURDATE()";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, studentId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}

// LAST 7 DAYS
public int getLast7DaysDistraction(int studentId) {

    String sql =
        "SELECT SUM(minutes) FROM distraction WHERE student_id=? AND date >= CURDATE() - INTERVAL 7 DAY";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, studentId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}

// HISTORY
public List<Distraction> getAllDistractions(int studentId) {

    List<Distraction> list = new ArrayList<>();

    String sql =
        "SELECT * FROM distraction WHERE student_id=? ORDER BY distraction_date DESC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, studentId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Distraction d = new Distraction();

            d.setDid(rs.getInt("did"));
            d.setStudentId(rs.getInt("student_id"));
            d.setType(rs.getString("type"));
            d.setMinutes(rs.getInt("minutes"));
            d.setDate(rs.getString("date"));

            list.add(d);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

// WEEKLY SUMMARY
public List<String[]> getWeeklySummary(int studentId) {

    List<String[]> list = new ArrayList<>();

    String sql =
        "SELECT date, SUM(minutes) total " +
        "FROM distraction " +
        "WHERE student_id=? " +
        "AND date >= CURDATE() - INTERVAL 7 DAY " +
        "GROUP BY date " +
        "ORDER BY date DESC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, studentId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            String[] row = new String[2];

            row[0] = rs.getString("date");
            row[1] = rs.getString("total");

            list.add(row);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

// DELETE DISTRACTION
public boolean deleteDistraction(int did) {

    boolean status = false;

    String sql =
        "DELETE FROM distraction WHERE did=?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, did);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            status = true;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return status;
}


}
