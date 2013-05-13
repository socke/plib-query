package de.feu.plib.xml.query;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * TODO check test, maybe we can delete that.
 */
public class QueryTypeTest {

	@Test
	public void testQueryType() {
		QueryType query = new QueryType();
		query.setItemDescription("Item Description");
		query.setClassRef("IRDI");
		query.getCharacteristicDataQueryExpression().add(new CharacteristicDataQueryExpressionType());
	}

}
