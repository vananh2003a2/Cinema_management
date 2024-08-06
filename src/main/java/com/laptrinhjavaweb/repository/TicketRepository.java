package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.entity.TicketEntity;

public interface TicketRepository extends JpaRepository<TicketEntity,Integer>{
	
	List<TicketEntity> findAll();
	@Query(nativeQuery = true, value = "SELECT * FROM Ticket WHERE id_Ticket = ?1")
    TicketEntity findTicket(int id);
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO ticket(BookingTime, refund, status, ticketCode, totalAmount, idMovie, idUser) VALUES (NOW(), 0, 0, ?1, 0, ?2, ?3)")
    void addTicket(String ticketCode, int idMovie, int idUser);
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO chairticket(idChairTime, idTicket) VALUES (?1, ?2)")
    void addTicketToChair(int idChairTime, int idTicket);
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO comboticket(idCombo, idTicket, quantity, sumPrice) VALUES (?1, ?2, ?3, ?4)")
    void addTicketToCombo(int idCombo, int idTicket, int quantity, float sumPrice);

    @Query(nativeQuery = true, value = "SELECT MAX(id_Ticket) FROM Ticket")
    int findLastInsertedTicket();
    //Thanh toán thành công
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE ticket SET refund = 1, status = 1, ticketCode = ?1, totalAmount = ?2 WHERE id_Ticket = ?3")
    void updateTicketSuccess(String ticketCode,Float totalAmount, int idTicket);
    //Hủy giao dịch
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE ticket SET refund = 0, status = 0, totalAmount = ?1 WHERE id_Ticket = ?2")
    void updateTransactionfailed(Float totalAmount, int idTicket);
    //Hủy thanh toán
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE ticket SET refund = 0, status = 0, totalAmount = ?1 WHERE id_Ticket = ?2")
    void updateTicketExit(Float totalAmount, int idTicket);
    //Thanh toán thất bại
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE ticket SET refund = 0, status = 2, ticketCode = ?1, totalAmount = ?2 WHERE id_Ticket = ?3")
    void updateTicketFail(String ticketCode,Float totalAmount, int idTicket);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM ticket WHERE id_Ticket = ?1")
    void deleteTicket(int idTicket);
    
    @Transactional
    @Modifying
    @Query(value = "CALL UpdateChairtimeStatus(:ticketId)", nativeQuery = true)
    void updateChairtimeStatus(@Param("ticketId") int ticketId);
}
