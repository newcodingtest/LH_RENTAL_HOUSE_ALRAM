package org.alram.lh.rental.infra.impl

import lombok.RequiredArgsConstructor
import mu.KotlinLogging
import org.alram.lh.rental.domain.LhNotice
import org.alram.lh.rental.infra.entity.RedisEntity
import org.alram.lh.rental.service.port.LhBatchRepository
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.PreparedStatement
import java.sql.SQLException

private val logger = KotlinLogging.logger {}

@Repository
class LhBatchRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate
): LhBatchRepository {

    @Transactional
    override fun save(notices: List<LhNotice>) {

        val sql = "UPDATE lhnotice set content = ? WHERE code = ? "

        jdbcTemplate.batchUpdate(sql,
            object : BatchPreparedStatementSetter {
                @Throws(SQLException::class)
                override fun setValues(ps: PreparedStatement, i: Int) {
                    val notice = notices.get(i)
                    logger.info { "template ${notice}"}
                    ps.setString(1, notice.content)
                    ps.setLong(2, notice.code)
                }

                override fun getBatchSize(): Int {
                    return 1
                }
            })

    }
}