COMPOSE_RUN_JAVA = docker-compose run --no-deps --rm java
COMPOSE_RUN_MVN = docker-compose run --no-deps --entrypoint "mvn" --rm java

.PHONY: version
version: ## Java version with docker run pattern
	$(COMPOSE_RUN_JAVA) --version

.PHONY: clean
clean: ## Maven Clean
	$(COMPOSE_RUN_MVN) clean

.PHONY: test
test: ## Maven Test
	$(COMPOSE_RUN_MVN) test

.PHONY: install
install: ## Maven Install
	$(COMPOSE_RUN_MVN) install