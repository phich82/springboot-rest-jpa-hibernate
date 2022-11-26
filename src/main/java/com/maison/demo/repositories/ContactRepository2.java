package com.maison.demo.repositories;

import com.maison.demo.mappers.ContactRowMapper;
import com.maison.demo.models.Contact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContactRepository2 {

    private final JdbcTemplate jdbcTemplate;

    public ContactRepository2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Contact> searchByStatus(String status) {
        String sql = "SELECT * FROM contact_message WHERE status = ?";
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, status);
            }
        }, new ContactRowMapper());
    }

    public int create(Contact contact) {
        String sql = "INSERT INTO contact_message (name, mobile_num, email, subject, message, status, created_at, created_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                contact.getName(),
                contact.getMobileNum(),
                contact.getEmail(),
                contact.getSubject(),
                contact.getMessage(),
                contact.getStatus(),
                contact.getCreatedAt(),
                contact.getCreatedBy()
        );
    }

    public int updateStatus(Integer id, String status, String updatedBy) {
        String sql = "UPDATE contact_message SET status = ?, updated_at = ?, updated_by = ? WHERE contact_id = ?";
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, status);
                ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(3, updatedBy);
                ps.setInt(4, id);
            }
        });
    }
}
