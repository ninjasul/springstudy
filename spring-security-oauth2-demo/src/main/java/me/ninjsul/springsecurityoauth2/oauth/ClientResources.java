package me.ninjsul.springsecurityoauth2.oauth;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

public class ClientResources {

    // @NestedConfigurationProperty: 단일 값이 아닌 중복값 바인딩 시 사용.
    // 소셜 미디어 세 곳의 프로퍼티를 각각 바인딩 하므로 해당 어노테이션을 사용.

    // client 기준으로 하위의 key/value 를 매핑
    @NestedConfigurationProperty
    private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

    // 원래 OAuth2 리소스 값을 매핑하는데 사용하지만 이 예제에서는 userInfoUri 값을 받는데 사용함.
    @NestedConfigurationProperty
    private ResourceServerProperties resource = new ResourceServerProperties();

    public AuthorizationCodeResourceDetails getClient() {
        return client;
    }

    public ResourceServerProperties getResource() {
        return resource;
    }
}