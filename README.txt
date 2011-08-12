When you create a default GAE project, it will include the following dependencies in the war/WEB-INF/folder. These are the basic JAR files you need to have a running GAE application.

appengine-api-1.0-sdk-1.5.1.jar
appengine-api-labs-1.5.1.jar
appengine-jsr107cache-1.5.1.jar
datanucleus-appengine-1.0.9.final.jar
datanucleus-core-1.1.5.jar
datanucleus-jpa-1.1.5.jar
geronimo-jpa_3.0_spec-1.1.1.jar
geronimo-jta_1.1_spec-1.1.1.jar
jdo2-api-2.3-eb.jar
jsr107cache-1.1.jar

As Twitter uses OAuth 1.0 for authentication, we need a library that will help us do that. I've choosen to use the Google API Client for Java library, and library from Google that is primarily used to access Google APIs, 
but it has very good OAuth 1.0 / 2.0 support, so we'll be using these features. It's also a library that can run in J2SE environments, as well as Android environments. Be sure to check it out. 

The Google API client for Java library needs the following JAR files to run, so we'll also place them in our war/WEB-INF/lib folder.

google-api-client-1.4.1-beta.jar
google-api-client-googleapis-1.4.1-beta.jar
gson-1.6.jar
guava-r09.jar
httpclient-4.0.3.jar
httpcore-4.0.1.jar
jackson-core-asl-1.6.7.jar
xpp3-1.1.4c.jar

Next up is the core Twitter for Java library. This is a very popular java based API for using Twitter. It also supports the Twitter OAuth 1.0 part for authentication. So we'll be using the Google API client for Java to guide us to the OAuth flow,
and will then pass on our access token to the Twitter4J library to do the actual calls to Twitter.

twitter4j-core-2.1.11.jar

Our sample application will use the JSF framework as a front-end technology. Not strictiy required, but I've always been interested in how JSF would run on GAE, so I decided to include it in this article providing the setup was relatively easy.
And it is. In order to run JSF 2.0, you need to add the following JAR files in the war/WEB-INF/lib folder.

el-api-2.2.jar
el-impl-2.2.jar
jsf-api.jar
jsf-impl.jar


OK, so far we've added all the runtime dependnecies to our project by placing them in the war/WEB-INF/lib folder. A subset of these dependencies will also need to be configured as compile-time dependencies, meaning we'll need to add them
to the build path of our application. The initial GAE project already includes a couple of compile-time dependencies, but we'll add the following :





In order to get JSF up & running you need to do the following :

Download a version of JSF (http://java.net/projects/javaserverfaces/downloads/download/release).
You'll need to download both the binaries and the sources. You need to sources in order to modify override the com.sun.faces.config.WebConfiguration class.
You can easily do this by taking a copy of the WebConfiguration.java file (from the source download), and place it in your GAE
project. 
[insert details what you need to do with it).

removing           if (canProcessJndiEntries())
                {
                        processJndiEntries(contextName);
                }
 blabla


http://code.google.com/p/joshjcarrier/source/browse/trunk/Sun+JSF+GAE/#Sun%20JSF%20GAE%2Fjsf-impl



If you want to work with the Jboss Richfaces archetype, make sure you have the following setup in your settings.xml, to ensure
that all the dependencies can be downloaded properly from the jboss site.
<settings xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <profiles>
<profile>
      <id>jboss-repo</id>
      <repositories>
        <repository>
          <id>jboss-repo</id>
          <name>jboss-repo</name>
          <url>http://repository.jboss.org/nexus/content/groups/public-jboss/</url>
          <layout>default</layout>
          <releases>
            <enabled>true</enabled>
            <updatePolicy>never</updatePolicy>
          </releases>
          <snapshots>
            <enabled>false</enabled>
            <updatePolicy>never</updatePolicy>
          </snapshots>
        </repository>
      </repositories>
	<pluginRepositories>
        <pluginRepository>
          <id>jboss-plugin-repo</id>
          <name>jboss-plugin-repo</name>
          <releases>
            <enabled>true</enabled>
            <updatePolicy>daily</updatePolicy>
            <checksumPolicy>warn</checksumPolicy>
          </releases>
          <snapshots>
            <enabled>true</enabled>
            <updatePolicy>daily</updatePolicy>
            <checksumPolicy>warn</checksumPolicy>
          </snapshots>
          <url>http://repository.jboss.org/nexus/content/groups/public-jboss/</url>
          <layout>default</layout>
        </pluginRepository>
      </pluginRepositories>	  
    </profile>  
  </profiles>
  
  
  
  <activeProfiles>
    <activeProfile>jboss-repo</activeProfile>
  </activeProfiles> 
  
</settings>



To use the archetype, just put yourself in a workspace folder, and execute the following command
mvn archetype:generate -DarchetypeGroupId=org.richfaces.archetypes -DarchetypeArtifactId=richfaces-archetype-gae -DarchetypeVersion=4.0.0.Final -DgroupId=com.ecs.appengine.richfaces -DartifactId=richfaces-gae -Dversion=1.0-SNAPSHOT

go into the artifactId folder (in this case richfaces-gae) and execute mvn clean install

it will create a WAR file, ready to be deployed on the appserver.




References
----------
Configuring JavaServer Faces 2.0 to run on the Google App Engine Using Eclipse
https://sites.google.com/a/wildstartech.com/adventures-in-java/Java-Platform-Enterprise-Edition/JavaServer-Faces/sun-javaserver-faces-reference-implementation/configuring-jsf-20-to-run-on-the-google-appengine

JavaServer Faces 2.0 and Google App Engine Compatibility Issues
https://sites.google.com/a/wildstartech.com/adventures-in-java/Java-Platform-Enterprise-Edition/JavaServer-Faces/sun-javaserver-faces-reference-implementation/configuring-jsf-20-to-run-on-the-google-appengine/javaserverfaces-20-and-google-app-engine-compatibility-issues

Getting started with Google App Engine and JSF
http://whileitcompiles.com/programming/java/getting-started-with-google-app-engine-and-jsf/

Mojarra JSF 2.0 RC2 and Google App Engine SDK 1.2.6
http://javadocs.wordpress.com/2009/10/17/mojarra-jsf-2-0-rc2-and-google-app-engine-sdk-1-2-6/

Google App Engine Tutorial for Java 
http://www.vogella.de/articles/GoogleAppEngineJava/article.html

RichFaces and GAE
http://community.jboss.org/wiki/HowToUseRichFaces40WithGoogleAppEngine

Maven Getting Started - Users
http://community.jboss.org/wiki/MavenGettingStarted-Users

JSF downloads
http://java.net/projects/javaserverfaces/downloads/download/release

RichFaces 4 Google Application Engine Archetype
http://anonsvn.jboss.org/repos/richfaces/branches/4.0.X/archetypes/rf-gae-sample/readme.txt

RichFaces 4 Google Application Engine Archetype pom.Xml	
http://anonsvn.jboss.org/repos/richfaces/trunk/archetypes/rf-gae-sample/src/main/resources/archetype-resources/pom.xml

RichFaces downloads
http://www.jboss.org/richfaces/download/stable.html

Google Application Engine Support with RichFaces 4
http://relation.to/17465.lace

Jboss Maven settings
http://community.jboss.org/wiki/MavenSettings

Jboss Nexus repo
https://repository.jboss.org/nexus

