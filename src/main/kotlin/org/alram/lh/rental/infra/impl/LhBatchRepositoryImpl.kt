package org.alram.lh.rental.infra.impl

import lombok.RequiredArgsConstructor
import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.infra.entity.RedisEntity
import org.alram.lh.rental.service.port.LhBatchRepository
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.PreparedStatement
import java.sql.SQLException

@Repository
@RequiredArgsConstructor
class LhBatchRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate
): LhBatchRepository {

    @Transactional
    override fun save(notices: List<LhNotice>) {

        val sql = "INSERT INTO product (id, content) " +
                "VALUES (?, ?)"

        jdbcTemplate.batchUpdate(sql,
            object : BatchPreparedStatementSetter {
                @Throws(SQLException::class)
                override fun setValues(ps: PreparedStatement, i: Int) {
                    var notice = notices.get(i)
                    ps.setLong(1, notice.id?:1)
                    ps.setString(2, notice.content)
                }

                override fun getBatchSize(): Int {
                    return 100;
                }
            })

    }
}