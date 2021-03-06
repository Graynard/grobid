package org.grobid.service.process;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.grobid.core.data.Affiliation;
import org.grobid.core.data.Date;
import org.grobid.core.data.Person;
import org.grobid.core.engines.Engine;
import org.grobid.service.util.GrobidServiceProperties;
import org.grobid.service.utils.GrobidRestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Damien
 * 
 */
public class GrobidRestProcessString {

	/**
	 * The class Logger.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GrobidRestProcessString.class);

	/**
	 * Parse a raw date and return the corresponding normalized date.
	 * 
	 * @param the
	 *            raw date string
	 * @return a response object containing the structured xml representation of
	 *         the date
	 */
	public static Response processDate(String date) {
		LOGGER.debug(methodLogIn());
		Response response = null;
		String retVal = null;
		boolean isparallelExec = GrobidServiceProperties.isParallelExec();
		try {
			LOGGER.debug(">> set raw date for stateless service'...");

			Engine engine = GrobidRestUtils.getEngine(isparallelExec);
			List<Date> dates;
			if (isparallelExec) {
				dates = engine.processDate(date);
				engine.close();
			} else {
				synchronized (engine) {
					dates = engine.processDate(date);
				}
			}
			if (dates != null) {
				if (dates.size() == 1)
					retVal = dates.get(0).toString();
				else
					retVal = dates.toString();
			}

			if (!GrobidRestUtils.isResultOK(retVal)) {
				response = Response.status(Status.NO_CONTENT).build();
			} else {
				response = Response.status(Status.OK).entity(retVal)
						.type(MediaType.TEXT_PLAIN).build();
			}
		} catch (Exception e) {
			LOGGER.error("An unexpected exception occurs. ", e);
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		LOGGER.debug(methodLogOut());
		return response;
	}

	/**
	 * Parse a raw sequence of names from a header section and return the
	 * corresponding normalized authors.
	 * 
	 * @param the
	 *            string of the raw sequence of header authors
	 * @return a response object containing the structured xml representation of
	 *         the authors
	 */
	public static Response processNamesHeader(String names) {
		LOGGER.debug(methodLogIn());
		Response response = null;
		String retVal = null;
		boolean isparallelExec = GrobidServiceProperties.isParallelExec();
		try {
			LOGGER.debug(">> set raw header author sequence for stateless service'...");

			Engine engine = GrobidRestUtils.getEngine(isparallelExec);
			List<Person> authors;
			if (isparallelExec) {
				authors = engine.processAuthorsHeader(names);
				engine.close();
			} else {
				synchronized (engine) {
					authors = engine.processAuthorsHeader(names);
				}
			}

			if (authors != null) {
				if (authors.size() == 1)
					retVal = authors.get(0).toString();
				else
					retVal = authors.toString();
			}

			if (!GrobidRestUtils.isResultOK(retVal)) {
				response = Response.status(Status.NO_CONTENT).build();
			} else {
				response = Response.status(Status.OK).entity(retVal)
						.type(MediaType.TEXT_PLAIN).build();
			}
		} catch (Exception e) {
			LOGGER.error("An unexpected exception occurs. ", e);
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		LOGGER.debug(methodLogOut());
		return response;
	}

	/**
	 * Parse a raw sequence of names from a header section and return the
	 * corresponding normalized authors.
	 * 
	 * @param the
	 *            string of the raw sequence of header authors.
	 * @return a response object containing the structured xml representation of
	 *         the authors
	 */
	public static Response processNamesCitation(String names) {
		LOGGER.debug(methodLogIn());
		Response response = null;
		String retVal = null;
		boolean isparallelExec = GrobidServiceProperties.isParallelExec();
		try {
			LOGGER.debug(">> set raw citation author sequence for stateless service'...");

			Engine engine = GrobidRestUtils.getEngine(isparallelExec);
			List<Person> authors;
			if (isparallelExec) {
				authors = engine.processAuthorsCitation(names);
				engine.close();
			} else {
				synchronized (engine) {
					authors = engine.processAuthorsCitation(names);
				}
			}

			if (authors != null) {
				if (authors.size() == 1)
					retVal = authors.get(0).toString();
				else
					retVal = authors.toString();
			}

			if (!GrobidRestUtils.isResultOK(retVal)) {
				response = Response.status(Status.NO_CONTENT).build();
			} else {
				response = Response.status(Status.OK).entity(retVal)
						.type(MediaType.TEXT_PLAIN).build();
			}
		} catch (Exception e) {
			LOGGER.error("An unexpected exception occurs. ", e);
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		LOGGER.debug(methodLogOut());
		return response;
	}
	
	/**
	 * Parse a raw sequence of affiliations and return the corresponding
	 * normalized affiliations with address.
	 * 
	 * @param the
	 *            string of the raw sequence of affiliation+address
	 * @return a response object containing the structured xml representation of
	 *         the affiliation
	 */
	public static Response processAffiliations(String affiliation) {
		LOGGER.debug(methodLogIn());
		Response response = null;
		String retVal = null;
		boolean isparallelExec = GrobidServiceProperties.isParallelExec();
		try {
			LOGGER.debug(">> set raw affiliation + address blocks for stateless service'...");

			Engine engine = GrobidRestUtils.getEngine(isparallelExec);
			List<Affiliation> affiliationList;
			if (isparallelExec) {
				affiliationList = engine.processAffiliation(affiliation);
				engine.close();
			} else {
				synchronized (engine) {
					affiliationList = engine.processAffiliation(affiliation);
				}
			}

			if (affiliationList != null) {
				if (affiliationList.size() == 1)
					retVal = affiliationList.get(0).toString();
				else
					retVal = affiliationList.toString();
			}
			if (!GrobidRestUtils.isResultOK(retVal)) {
				response = Response.status(Status.NO_CONTENT).build();
			} else {
				response = Response.status(Status.OK).entity(retVal)
						.type(MediaType.TEXT_PLAIN).build();
			}
		} catch (Exception e) {
			LOGGER.error("An unexpected exception occurs. ", e);
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		LOGGER.debug(methodLogOut());
		return response;
	}

	/**
	 * @return
	 */
	public static String methodLogIn() {
		return ">> " + GrobidRestProcessString.class.getName() + "."
				+ Thread.currentThread().getStackTrace()[1].getMethodName();
	}

	/**
	 * @return
	 */
	public static String methodLogOut() {
		return "<< " + GrobidRestProcessString.class.getName() + "."
				+ Thread.currentThread().getStackTrace()[1].getMethodName();
	}

}
