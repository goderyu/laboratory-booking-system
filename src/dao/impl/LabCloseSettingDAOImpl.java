package dao.impl;

import dao.ILabCloseSettingDAO;
import vo.LabCloseSetting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LabCloseSettingDAOImpl implements ILabCloseSettingDAO {
    private final Connection conn;
    private PreparedStatement preparedStatement = null;

    public LabCloseSettingDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public boolean insertLabCloseSetting(LabCloseSetting labCloseSetting) {
        boolean flag = false;
        try {
            String sql = "insert into 实验室关闭时间表  values(?,?,?,?,?)";
            this.preparedStatement = this.conn.prepareStatement(sql);
            this.preparedStatement.setString(1, labCloseSetting.getLabName());
            this.preparedStatement.setInt(2, labCloseSetting.getStartWeek());
            this.preparedStatement.setInt(3, labCloseSetting.getEndWeek());
            this.preparedStatement.setInt(4, labCloseSetting.getKeSum());
            this.preparedStatement.setInt(5, labCloseSetting.getDayOfWeekSum());
            if (this.preparedStatement.executeUpdate() > 0) {
                flag = true;
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
        return flag;
    }
}
