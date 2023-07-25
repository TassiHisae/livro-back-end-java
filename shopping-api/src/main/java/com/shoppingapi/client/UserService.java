package com.shoppingapi.client;

import com.dto.UserDTO;
import com.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Value("${USER_API_URL:http://localhost:8081/user/}")
    private String userApiUrl;

    public UserDTO getUserByCpfAndKey(String cpf, String key){
        try {
            WebClient webClient = WebClient.builder()
                    .baseUrl(userApiUrl)
                    .build();

            Mono<UserDTO> user = webClient.get()
                    .uri( "cpf/" + cpf + "?key=" + key)
                    .retrieve()
                    .bodyToMono(UserDTO.class);

            return user.block();
        }catch (HttpClientErrorException.NotFound ex){
            throw new UserNotFoundException();
        }
    }
}
