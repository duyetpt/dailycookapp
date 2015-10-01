package com.vn.dailycookapp;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.AfterClass;

import com.vn.dailycookapp.utils.ConfigurationLoader;
import com.vn.dailycookapp.utils.ErrorCodeConstant;

import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongoImportExecutable;
import de.flapdoodle.embed.mongo.MongoImportProcess;
import de.flapdoodle.embed.mongo.MongoImportStarter;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongoImportConfig;
import de.flapdoodle.embed.mongo.config.MongoImportConfigBuilder;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.Timeout;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class AbstractTest extends JerseyTest {
	protected String					responseData;
	
	static Command						command			= Command.MongoD;
	// static IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
	// .defaults(command)
	// .artifactStore(new ExtractedArtifactStoreBuilder()
	// .defaults(command)
	// .download(new DownloadConfigBuilder()
	// .defaultsForCommand(command)
	// .proxyFactory(new HttpProxyFactory("proxy", 8080))))
	// .build();
	/**
	 * please store Starter or RuntimeConfig in a static final field
	 * if you want to use artifact store caching (or else disable caching)
	 */
	// private static final MongodStarter starter =
	// MongodStarter.getInstance(runtimeConfig);
	
	private static final MongodStarter	starter			= MongodStarter.getDefaultInstance();
	private static final String			DATABASE_NAME	= "dailycook";
	private static final int			PORT			= 27017;
	
	private static MongodExecutable		_mongodExe;
	private static MongodProcess		_mongod;
	
	@BeforeClass
	public static void set() throws Exception {
		
		_mongodExe = starter.prepare(new MongodConfigBuilder().version(Version.Main.PRODUCTION)
				.net(new Net(PORT, Network.localhostIsIPv6())).timeout(new Timeout(50000)).build());
		_mongod = _mongodExe.start();
	}
	
	@AfterClass
	public static void tear() {
		_mongod.stop();
		_mongodExe.stop();
	}
	
	@Override
	protected Application configure() {
		ConfigurationLoader.getInstance()
				.setLanguagePath("E:\\git_daily_cook_repo\\DailyCookApp\\src\\resources\\lang");
		return new ResourceConfig(getService());
	}
	
	protected Class<?> getService() {
		return null;
	}
	
	protected JSONObject getResponse() {
		JSONObject jsonObj = new JSONObject(responseData);
		int errorCode = jsonObj.getInt("error_no");
		assertEquals(ErrorCodeConstant.SUCCESSUL.getErrorCode(), errorCode);
		
		return jsonObj;
	}
	
	@Test
	public void test() {
	};
	
	/**
	 * import data for test but not close mongod
	 * @param collectionName
	 * @param dataFile
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void importData(String collectionName, String dataFile) throws UnknownHostException, IOException {
		
		MongoImportProcess mongoImport = startMongoImport(PORT, DATABASE_NAME, collectionName, dataFile, false, true,
				true);
		// try {
		// MongoClient mongoClient = new MongoClient("localhost", PORT);
		// System.out.println("DB Names: " + mongoClient.getDatabaseNames());
		// } finally {
		// // mongoImport.stop();
		// }
	}
	
	private MongoImportProcess startMongoImport(int port, String dbName, String collection, String jsonFile,
			Boolean jsonArray, Boolean upsert, Boolean drop) throws UnknownHostException, IOException {
		IMongoImportConfig mongoImportConfig = new MongoImportConfigBuilder().version(Version.Main.PRODUCTION)
				.net(new Net(port, Network.localhostIsIPv6())).db(dbName).collection(collection).upsert(upsert)
				.dropCollection(drop).jsonArray(jsonArray).importFile(jsonFile).timeout(new Timeout(40000)).build();
		
		MongoImportExecutable mongoImportExecutable = MongoImportStarter.getDefaultInstance()
				.prepare(mongoImportConfig);
		MongoImportProcess mongoImport = mongoImportExecutable.start();
		return mongoImport;
	}
}
