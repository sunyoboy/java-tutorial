mvn archetype:generate -DgroupId=org.lieve -DartifactId=netty-server -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>${slf4j.version}</version>
        <scope>compile</scope>
　　</dependency>
　　<dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-core</artifactId>
        <version>${logback.version}</version>
    </dependency>
    <dependency>
　　　　　<groupId>ch.qos.logback</groupId>
　　　　　<artifactId>logback-classic</artifactId>
　　　　  <version>${logback.version}</version>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
    </dependency>

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>