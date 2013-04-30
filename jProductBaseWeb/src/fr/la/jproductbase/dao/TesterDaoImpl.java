package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.metier.Tester;

public class TesterDaoImpl extends GenericDao implements TesterDao {

	ConnectionTester cnxTester;

	public TesterDaoImpl(ConnectionTester cnxTester) {
		this.cnxTester = cnxTester;
	}

	@Override
	public Tester getTester(int idTester) {
		Tester _tester = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM tester " + "WHERE (idTester=?)");
			_stmt.setInt(1, idTester);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_tester = this.getTester(_rs);
			} else {
				_tester = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _tester;
	}

	@Override
	public Tester getTester(String name) {
		Tester _tester = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM tester WHERE (name=?)");
			_stmt.setString(1, name);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_tester = this.getTester(_rs);
			} else {
				_tester = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _tester;
	}

	@Override
	public List<Tester> getActiveTesters() {
		List<Tester> _testers = new ArrayList<Tester>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM tester WHERE (state = 1)");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Tester _tester = this.getTester(_rs);
				_testers.add(_tester);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _testers;
	}
	
	@Override
	public List<Tester> getTesters() {
		List<Tester> _testers = new ArrayList<Tester>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM tester");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Tester _tester = this.getTester(_rs);
				_testers.add(_tester);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _testers;
	}

	/*
	 * Cr&eacute;er un testeur &agrave; partir d'un enregistement de la base de
	 * donn&eacute;es.
	 * 
	 * @param rs Enregistement de la base de donn&eacute;es.
	 * 
	 * @return Testeur.
	 */
	private Tester getTester(ResultSet rs) throws SQLException {
		int _idTester = rs.getInt("idTester");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _name = rs.getString("name");
		Tester _tester = new Tester(_idTester, _timestamp, _state, _name);

		return _tester;
	}

	@Override
	public Tester addTester(String name) {
		/*Tester _tester = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxTester.getCnx().prepareStatement(
					"INSERT INTO tester (state, name)" + " VALUES (?, ?)");
			_stmt.setInt(1, 1);
			_stmt.setString(2, name);
			_stmt.executeUpdate();

			// Retrieve testerReport data
			_stmt = this.cnxTester.getCnx().prepareStatement(
					"SELECT * FROM tester WHERE (name=?)");
			_stmt.setString(1, name);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_tester = this.getTester(_rs);
			} else {
				throw new TesterDaoException(exceptionMsg);
			}
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _tester;*/
		return this.addTester(name, 1);
	}
	
	@Override
	public Tester addTester(String name, int state) {
		Tester _tester = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO tester (state, name)" + " VALUES (?, ?)");
			_stmt.setInt(1, state);
			_stmt.setString(2, name);
			_stmt.executeUpdate();

			// Retrieve testerReport data
			_stmt = c.prepareStatement(
					"SELECT * FROM tester WHERE (name=?) AND (state=?)");
			_stmt.setString(1, name);
			_stmt.setInt(2, state);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_tester = this.getTester(_rs);
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

		return _tester;
	}
	
	@Override
	public void updateTester(Tester tester) {
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE tester " + "SET state=?, name=? "
							+ " WHERE (idTester=?)");
			_stmt.setInt(1, tester.getState());
			_stmt.setString(2, tester.getName());
			_stmt.setInt(3, tester.getIdTester());
			_stmt.executeUpdate();

			// Update object
			_stmt = c.prepareStatement(
					"SELECT * FROM tester"
							+ " WHERE (idTester=?)");
			_stmt.setInt(1, tester.getIdTester());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getTester(_rs);
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
