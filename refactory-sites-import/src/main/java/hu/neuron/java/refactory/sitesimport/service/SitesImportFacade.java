package hu.neuron.java.refactory.sitesimport.service;

import hu.neuron.java.refactory.sitesimport.exception.SitesImportException;
import hu.neuron.java.refactory.vo.TicketVO;

import java.io.IOException;
import java.util.List;

public interface SitesImportFacade {
	List<TicketVO> importTicketsFromUrl(String url) throws SitesImportException, IOException;
}
