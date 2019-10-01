build:
	mvn install

deploy:
	gpg --import .travis/private-key.gpg
	mvn versions:set -DnewVersion=${TRAVIS_TAG}
	mvn clean deploy -P release -DskipTests=true -B -U --settings .travis/settings.xml

