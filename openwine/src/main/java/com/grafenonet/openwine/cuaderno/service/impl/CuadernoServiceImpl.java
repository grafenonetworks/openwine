package com.grafenonet.openwine.cuaderno.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grafenonet.openwine.cuaderno.domain.Gasoleo;
import com.grafenonet.openwine.cuaderno.service.CuadernoService;
import com.grafenonet.openwine.cuaderno.service.GasoleoService;

@Service
public class CuadernoServiceImpl implements CuadernoService {
	
	private static Logger LOG = LoggerFactory.getLogger(CuadernoServiceImpl.class);
	
	@Autowired
	private GasoleoService ticketGasoilService;

	@Override
	public Gasoleo getResumenTickets(int year) {
		LOG.debug("Iniciando getResumenTickets ...");
		
		BigDecimal importe = new BigDecimal(0);
		BigDecimal litros = new BigDecimal(0);
		Date date = new Date();
		
		List<Gasoleo> tickets = ticketGasoilService.list(year);
		for (Gasoleo ticket : tickets) {
			
				importe = importe.add(ticket.getImporte());
				litros = litros.add(ticket.getLitros());
			
		}
		
		Gasoleo ticketGasoil = new Gasoleo();
		ticketGasoil.setFecha(date);
		ticketGasoil.setImporte(importe);
		ticketGasoil.setLitros(litros);
				
		LOG.debug("Finalizando getResumenTickets.");
		return ticketGasoil;
		
	}

}
