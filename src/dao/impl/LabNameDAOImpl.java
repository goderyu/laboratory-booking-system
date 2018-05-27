package dao.impl;

import dao.ILabNameDAO;
import vo.LabName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LabNameDAOImpl implements ILabNameDAO {
    private final Connection conn;
    private PreparedStatement preparedStatement = null;

    public LabNameDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<LabName> selectLabName() {
        ArrayList<LabName> labNameList = new ArrayList<>();
        String sql = "select 实验室编号  from 实验室信息表";
        try {
            this.preparedStatement = this.conn.prepareStatement(sql);
            ResultSet resultSet = this.preparedStatement.executeQuery();
            while (resultSet.next()) {
                LabName labName = new LabName();
                labName.setLabName(resultSet.getString(1));
                labNameList.add(labName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return labNameList;
    }
}