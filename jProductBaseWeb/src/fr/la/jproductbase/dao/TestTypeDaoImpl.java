package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.metier.TestType;

public class TestTypeDaoImpl extends GenericDao implements TestTypeDao {

	ConnectionTester cnxTester;

	public TestTypeDaoImpl(ConnectionTester cnxTester) {
		this.cnxTester = cnxTester;
	}

	@Override
	public TestType getTestType(int idTestType) {
		TestType _testType = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM testType WHERE idTestType=?");
			_stmt.setInt(1, idTestType);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_testType = this.getTestType(_rs);
			} else {
				_testType = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _testType;
	}

	@Override
	public TestType getTestType(String name) {
		TestType _testType = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM testType WHERE name=?");
			_stmt.setString(1, name);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_testType = this.getTestType(_rs);
			} else {
				_testType = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _testType;
	}

	@Override
	public List<TestType> getTestTypes() {
		List<TestType> _testTypes = new ArrayList<TestType>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM testType");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				TestType _testType = this.getTestType(_rs);
				_testTypes.add(_testType);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _testTypes;
	}

	/*
	 * Cr&eacute;er un type de test &agrave; partir d'un enregistement de la
	 * base de donn&eacute;es.
	 * 
	 * @param rs Enregistement de la base de donn&eacute;es.
	 * 
	 * @return Type de test.
	 */
	private TestType getTestType(ResultSet rs) throws SQLException {
		int _idTestType = rs.getInt("idTestType");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _name = rs.getString("name");
		boolean _needTester = rs.getBoolean("needTester");
		TestType _testType = new TestType(_idTestType, _timestamp, _state,
				_name, _needTester);

		return _testType;
	}

	// 18-01-12 : RMO : Création de la méthode
	@Override
	public TestType addTestType(String name, int state, boolean needTester) {
		TestType _testType = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement("INSERT INTO testType (state, name, needTester) VALUES (?, ?, ?)");
			_stmt.setInt(1, state);
			_stmt.setString(2, name);
			_stmt.setBoolean(3, needTester);
			_stmt.executeUpdate();

			// Retrieve testerReport data
			_stmt = c.prepareStatement("SELECT * FROM testType WHERE (name=?) AND (state=?) AND (needTester=?)");
			_stmt.setString(1, name);
			_stmt.setInt(2, state);
			_stmt.setBoolean(3, needTester);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_testType = this.getTestType(_rs);
			} else {
				throw new IllegalStateException();
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _testType;
	}

	// 18-01-12 : RMO : Création de la méthode
	@Override
	public void updateTestType(TestType testType) {
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE testType " + "SET state=?, name=?, needTester=? "
							+ " WHERE (idTestType=?)");
			_stmt.setInt(1, testType.getState());
			_stmt.setString(2, testType.getName());
			_stmt.setBoolean(3, testType.isNeedTester());
			_stmt.setInt(4, testType.getIdTestType());
			_stmt.executeUpdate();

			// Update object
			_stmt = c.prepareStatement(
					"SELECT * FROM testType" + " WHERE (idTestType=?)");
			_stmt.setInt(1, testType.getIdTestType());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getTestType(_rs);
			} else {
				throw new IllegalStateException();
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
	}
}
