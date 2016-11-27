package co.rcbike.usuarios.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.google.common.base.Strings;

import co.rcbike.autenticacion.service.AutenticacionService;
import co.rcbike.usuarios.model.RegistroUsuario;
import co.rcbike.usuarios.model.Usuario;

@Stateless
public class UsuariosService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private AutenticacionService autenticacion;

    public Usuario findUsuario(String email) {
        TypedQuery<Usuario> q = em.createNamedQuery(Usuario.SQ_findByEmail, Usuario.class);
        q.setParameter(Usuario.SQ_PARAM_EMAIL, email);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Listar todos los usuarios del sistema
     * 
     * @return lista con todos los usuarios del sistema (no null)
     */
    public List<Usuario> listUsuario() {
        TypedQuery<Usuario> q = em.createNamedQuery(Usuario.SQ_listByNombre, Usuario.class);
        return q.getResultList();
    }

    public List<Usuario> listUsuarios(List<String> emails) {
        TypedQuery<Usuario> q = em.createNamedQuery(Usuario.SQ_listUsuarios, Usuario.class);
        q.setParameter(Usuario.SQ_PARAM_EMAILS, emails);
        return Collections.unmodifiableList(q.getResultList());
    }

    public List<Usuario> listUsuariosFiltro(String filtro) {
        if (Strings.isNullOrEmpty(filtro)) {
            return listUsuario();
        } else {
            TypedQuery<Usuario> q = em.createNamedQuery(Usuario.SQ_listMatchFiltro, Usuario.class);
            q.setParameter(Usuario.SQ_PARAM_FILTRO, "%" + filtro.toLowerCase() + "%");
            return Collections.unmodifiableList(q.getResultList());
        }
    }
    public List<Usuario> listAmigos(String email) {
        List<Usuario> amigos = findUsuario(email).getAmigos();
        amigos.size();
        return new ArrayList<>(amigos);
    }

    public List<Usuario> listNoAmigosDe(String email, String filtro) {
        Usuario usuario = findUsuario(email);

        List<String> emailExcluidos = listAmigos(email).stream().map(item -> item.getEmail())
                .collect(Collectors.toList());

        emailExcluidos.add(usuario.getEmail());

        TypedQuery<Usuario> q = null;
        if (!Strings.isNullOrEmpty(filtro)) {
            q = em.createNamedQuery(Usuario.SQ_listNoAmigosFiltro, Usuario.class);
            q.setParameter(Usuario.SQ_PARAM_FILTRO, "%" + filtro.toLowerCase() + "%");
        } else {
            q = em.createNamedQuery(Usuario.SQ_listNoAmigos, Usuario.class);
        }
        q.setParameter(Usuario.SQ_PARAM_EXCLUIDOS, emailExcluidos);
        return q.getResultList();
    }

    public void crearUsuario(RegistroUsuario registroUsuario) {
        Usuario usuario = new Usuario();
        usuario.setEmail(registroUsuario.getEmail());
        usuario.setNombres(registroUsuario.getNombres());
        usuario.setApellidos(registroUsuario.getApellidos());
        usuario.setFoto(registroUsuario.getFoto());
        em.persist(usuario);
        if (registroUsuario.getClave() != null) {
            autenticacion.registrar(registroUsuario.getEmail(), registroUsuario.getClave());
        }
    }

    public void removerAmigo(String emailUsuario, String emailAmigo) {
        Usuario usuario = findUsuario(emailUsuario);
        Usuario amigo = findUsuario(emailAmigo);

        usuario.getAmigos().remove(amigo);
        em.merge(amigo);
    }

    public void agregarAmigo(String emailUsuario, String emailAmigo) {
        Usuario usuario = findUsuario(emailUsuario);
        Usuario amigo = findUsuario(emailAmigo);

        usuario.getAmigos().add(amigo);
        em.merge(amigo);
    }
}
