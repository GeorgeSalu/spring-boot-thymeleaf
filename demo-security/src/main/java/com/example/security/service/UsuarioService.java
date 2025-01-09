package com.example.security.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.security.datatables.Datatables;
import com.example.security.datatables.DatatablesColunas;
import com.example.security.domain.Perfil;
import com.example.security.domain.PerfilTipo;
import com.example.security.domain.Usuario;
import com.example.security.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private Datatables datatables;
	
	@Transactional(readOnly = true)
	public Usuario buscarPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = buscarPorEmail(username);
		
		return new User(
				usuario.getEmail(), 
				usuario.getSenha(), 
				AuthorityUtils.createAuthorityList(getAuthorities(usuario.getPerfis()))
		);
	}
	
	
	private String[] getAuthorities(List<Perfil> perfis) {
		String[] authorities = new String[perfis.size()];
		for(int i = 0;i < perfis.size(); i++) {
			authorities[i] = perfis.get(i).getDesc();
		}
		return authorities;
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarTodos(HttpServletRequest request) {

		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.USUARIOS);
		
		Page<Usuario> page = datatables.getSearch().isEmpty()
				? usuarioRepository.findAll(datatables.getPageable())
				: usuarioRepository.findByEmailOrPerfil(datatables.getSearch(), datatables.getPageable());
		
		return datatables.getResponse(page);
	}

	@Transactional(readOnly = false)
	public void salvarUsuario(Usuario usuario) {
		
		String crypt = new BCryptPasswordEncoder().encode(usuario.getSenha());
		
		usuario.setSenha(crypt);
		usuarioRepository.save(usuario);
	}

	@Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {

		return usuarioRepository.findById(id).get();
    }

	@Transactional(readOnly = true)
	public Usuario buscarPorIdEPerfis(Long usuarioId, Long[] perfisId) {

		return usuarioRepository.findByIdAndPerfis(usuarioId, perfisId)
				.orElseThrow(() -> new UsernameNotFoundException("usuario inexistente"));
	}

	public static boolean isSenhaCorreta(String senhaDigitada, String senhaArmazenada) {
		
		return new BCryptPasswordEncoder().matches(senhaDigitada, senhaArmazenada);
	}

	@Transactional(readOnly = false)
	public void alterarSenha(Usuario usuario, String senha) {
		
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		
		usuarioRepository.save(usuario);
	}

	@Transactional(readOnly = false)
	public void salvarCadastroPaciente(Usuario usuario) {
		
		String crypt = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(crypt);
		usuario.addPerfil(PerfilTipo.PACIENTE);
		
		usuarioRepository.save(usuario);
		
	}
}




