package hu.neuron.java.refactory.sitesimport.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hu.neuron.java.refactory.sitesimport.exception.SitesImportException;
import hu.neuron.java.refactory.sitesimport.service.SitesImportFacade;
import hu.neuron.java.refactory.vo.TicketVO;

public class SitesImportFacadeImpl implements SitesImportFacade {

	@Override
	public List<TicketVO> importTicketsFromUrl(String url) throws SitesImportException, IOException {
		BufferedReader in = null;
		List<TicketVO> ret = null;
		
		try {
			URL oracle = new URL(url);
			in = new BufferedReader(new InputStreamReader(oracle.openStream()));

			StringBuffer input = new StringBuffer();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				input.append(inputLine);
			}

			Document doc = Jsoup.parse(input.toString());
			Elements tables = doc.select("table.bodyTable");

			Iterator<Element> i = tables.iterator();
			
			ret = new ArrayList<TicketVO>();
			while (i.hasNext()) {
				TicketVO ticket = new TicketVO();
				
				ticket.setDescription(i.next().toString());
				
				ret.add(ticket);
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new SitesImportException();
		} finally {
			in.close();
		}
		
		return ret;
	}

}
