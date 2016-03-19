package org.dziadzi;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;

@Configuration
@EnableNeo4jRepositories
class ApplicationConfig extends Neo4jConfiguration {


	@Override
	public Neo4jServer neo4jServer() {
		return new RemoteServer("http://wozek:iRe10PTvpllgRLREXkha@wozek.sb04.stations.graphenedb.com:24789");
	}

	@Override
	public SessionFactory getSessionFactory() {
		return new SessionFactory("org.dziadzi.nodes");
	}


}
