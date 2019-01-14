package com.krista.spring.cloud.gateway.sample.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
import org.springframework.cloud.gateway.handler.predicate.PathRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

/**
 * RouteConfiguration
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/14 15:05
 */
@Component
public class RouteConfiguration implements ApplicationEventPublisherAware {
    /**
     * default impl is InMemoryRouteDefinitionRepository
     */
    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        eventPublisher = applicationEventPublisher;
    }

    public String save() {
        RouteDefinition definition = new RouteDefinition();
        PredicateDefinition predicate = new PredicateDefinition();

        definition.setId("baiduRoute");
        predicate.setName(NameUtils.normalizeRoutePredicateName(PathRoutePredicateFactory.class));
        predicate.addArg("pattern", "/yy/**");

        definition.setPredicates(Arrays.asList(predicate));

        URI uri = UriComponentsBuilder.fromHttpUrl("http://www.yy.com").build().toUri();
        definition.setUri(uri);

        FilterDefinition filter = new FilterDefinition();
        filter.setName(NameUtils.normalizeFilterFactoryName(StripPrefixGatewayFilterFactory.class));
        filter.addArg(StripPrefixGatewayFilterFactory.PARTS_KEY, "1");
        definition.setFilters(Collections.singletonList(filter));

        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.eventPublisher.publishEvent(new RefreshRoutesEvent(this));

        return "success";
    }
}
