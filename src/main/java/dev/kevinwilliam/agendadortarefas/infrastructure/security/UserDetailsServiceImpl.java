package dev.kevinwilliam.agendadortarefas.infrastructure.security;


import dev.kevinwilliam.agendadortarefas.business.dto.UsuarioDTO;
import dev.kevinwilliam.agendadortarefas.infrastructure.client.UsuarioClient;
import dev.kevinwilliam.usuario.infrastructure.entity.Usuario;
import dev.kevinwilliam.usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  {

    // Repositório para acessar dados de usuário no banco de dados
    @Autowired
    private UsuarioClient client;

    // Implementação do método para carregar detalhes do usuário pelo e-mail
    @Override
   /* public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo e-mail
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        // Cria e retorna um objeto UserDetails com base no usuário encontrado
        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuario.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }*/

    public UserDetails loadUserByUsername(String email, String token)  {
        // Busca o usuário no banco de dados pelo e-mail
        UsuarioDTO usuarioDTO = client.buscarUsuarioPorEmail(email, token);

        // Cria e retorna um objeto UserDetails com base no usuário encontrado
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
