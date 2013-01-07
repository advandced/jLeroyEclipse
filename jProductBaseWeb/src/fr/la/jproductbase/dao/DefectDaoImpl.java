package fr.la.jproductbase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.Defect;
import fr.la.jproductbase.metier.TesterReport;

public class DefectDaoImpl implements DefectDao {
	private static String exceptionMsg = "Défaut inconnu dans la base de données.";

	private ConnectionTester cnxTester;

	public DefectDaoImpl(ConnectionTester cnxTester) {
		this.cnxTester = cnxTester;
	}

	@Override
	public List<Defect> getDefects(TesterReport testerReport)
			throws SQLException {
		List<Defect> _defects = new ArrayList<Defect>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			int _idTesterReport = testerReport.getIdTesterReport();
			_stmt = this.cnxTester.getCnx().prepareStatement(
					"SELECT * FROM defect " + "WHERE (idTesterReport=?)");
			_stmt.setInt(1, _idTesterReport);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Defect _defect = this.getDefect(_rs);
				_defects.add(_defect);

				// Update failureReport object
				testerReport.addDefect(_defect);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _defects;
	}

	@Override
	public Defect addDefect(Defect defect, TesterReport testerReport)
			throws SQLException, DefectDaoException {
		Defect _defect = null;
		if (null != defect) {
			PreparedStatement _stmt = null;
			ResultSet _rs = null;

			int _idTesterReport = testerReport.getIdTesterReport();
			try {
				_stmt = this.cnxTester
						.getCnx()
						.prepareStatement(
								"INSERT INTO defect (state, sequence, testName, function, value, idTesterReport)"
										+ " VALUES (?, ?, ?, ?, ?, ?)");
				_stmt.setInt(1, defect.getState());
				_stmt.setString(2, defect.getSequence());
				_stmt.setString(3, defect.getTestName());
				_stmt.setString(4, defect.getFunction());
				_stmt.setString(5, defect.getValue());
				_stmt.setInt(6, _idTesterReport);
				_stmt.executeUpdate();

				// Retrieve testerReport data
				_stmt = this.cnxTester.getCnx().prepareStatement(
						"SELECT * FROM defect" + " WHERE (idTesterReport=?)"
								+ "	AND (sequence=?)" + " AND (testName=?)"
								+ " AND (function=?)");
				_stmt.setInt(1, _idTesterReport);
				_stmt.setString(2, defect.getSequence());
				_stmt.setString(3, defect.getTestName());
				_stmt.setString(4, defect.getFunction());

				_rs = _stmt.executeQuery();
				if (_rs.next()) {
					_defect = this.getDefect(_rs);
					
					// Update testerReport
					testerReport.addDefect(_defect);
				} else {
					throw new DefectDaoException(exceptionMsg);
				}
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (null != _rs) {
					_rs.close();
				}
				if (null != _stmt) {
					_stmt.close();
				}
			}
		} else {
			// No testerReport
		}

		return _defect;
	}

	/*
	 * Cr&eacute;er un d&eacute;faut &agrave; partir d'un enregistrement de la
	 * base de donn&eacute;es.
	 * 
	 * @param rs Enregistrement de la base de donn&eacute;es.
	 * 
	 * @return D&eacute;faut.
	 * 
	 * @throws SQLException
	 */
	private Defect getDefect(ResultSet rs) throws SQLException {
		// Defect
		int _idDefect = rs.getInt("idDefect");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _sequence = rs.getString("sequence");
		String _testName = rs.getString("testName");
		String _function = rs.getString("function");
		String _value = rs.getString("value");
		Defect _defect = new Defect(_idDefect, _timestamp, _state, _sequence,
				_testName, _function, _value);

		return _defect;
	}
}
