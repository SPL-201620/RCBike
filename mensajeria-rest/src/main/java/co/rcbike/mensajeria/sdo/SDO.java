/*
 * Logike
 * Copyrigth .2015.
 */
package co.rcbike.mensajeria.sdo;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 * description
 *
 * @author: Diego Poveda <diego.poveda@logike.co>
 * @param <T>
 * @param <K>
 * @version: 1.0
 * @since: 1.0
 */
public interface SDO<T, K> {

  /**
   *
   * @param em
   * @param id
   * @param entityClass
   * @return
   */
  T find(EntityManager em, K id, Class<T> entityClass);

  /**
   *
   * @param em
   * @param namedQuery
   * @return
   */
  T findByNamedQuery(EntityManager em, String namedQuery);

  /**
   *
   * @param em
   * @param namedQuery
   * @param params
   * @return
   */
  T findByNamedQuery(EntityManager em, String namedQuery, Map<String, Object> params);

  /**
   *
   * @param em
   * @param namedQuery
   * @param params
   * @param entityClass
   * @return
   */
  T findByNamedQuery(EntityManager em, String namedQuery, Map<String, Object> params, Class<T> entityClass);

  /**
   *
   * @param em
   * @param jpqlQuery
   * @return
   */
  T findByJPQLQuery(EntityManager em, String jpqlQuery);

  /**
   *
   * @param em
   * @param jpqlQuery
   * @param params
   * @return
   */
  T findByJPQLQuery(EntityManager em, String jpqlQuery, Map<String, Object> params);

  /**
   *
   * @param em
   * @param jpqlQuery
   * @param params
   * @param entityClass
   * @return
   */
  T findByJPQLQuery(EntityManager em, String jpqlQuery, Map<String, Object> params, Class<T> entityClass);

  /**
   *
   * @param em
   * @param nativeQuery
   * @return
   */
  T findByNativeQuery(EntityManager em, String nativeQuery);

  /**
   *
   * @param em
   * @param nativeQuery
   * @param params
   * @return
   */
  T findByNativeQuery(EntityManager em, String nativeQuery, Object... params);

  /**
   *
   * @param em
   * @param nativeQuery
   * @param entityClass
   * @param params
   * @return
   */
  T findByNativeQuery(EntityManager em, String nativeQuery, Class<T> entityClass, Object... params);

  /**
   *
   * @param em
   * @param entityClass
   * @return
   */
  List<T> getResultList(EntityManager em, Class<T> entityClass);

  /**
   *
   * @param em
   * @param namedQuery
   * @return
   */
  List<T> getResultListByNamedQuery(EntityManager em, String namedQuery);

  /**
   *
   * @param em
   * @param namedQuery
   * @param params
   * @return
   */
  List<T> getResultListByNamedQuery(EntityManager em, String namedQuery, Map<String, Object> params);

  /**
   *
   * @param em
   * @param namedQuery
   * @param params
   * @param entityClass
   * @return
   */
  List<T> getResultListByNamedQuery(EntityManager em, String namedQuery, Map<String, Object> params, Class<T> entityClass);

  /**
   *
   * @param em
   * @param jpqlQuery
   * @return
   */
  List<T> getResultListByJPQLQuery(EntityManager em, String jpqlQuery);

  /**
   *
   * @param em
   * @param jpqlQuery
   * @param params
   * @return
   */
  List<T> getResultListByJPQLQuery(EntityManager em, String jpqlQuery, Map<String, Object> params);

  /**
   *
   * @param em
   * @param jpqlQuery
   * @param params
   * @param entityClass
   * @return
   */
  List<T> getResultListByJPQLQuery(EntityManager em, String jpqlQuery, Map<String, Object> params, Class<T> entityClass);

  /**
   *
   * @param em
   * @param nativeQuery
   * @return
   */
  List<T> getResultListByNativeQuery(EntityManager em, String nativeQuery);

  /**
   *
   * @param em
   * @param nativeQuery
   * @param params
   * @return
   */
  List<T> getResultListByNativeQuery(EntityManager em, String nativeQuery, Object... params);

  /**
   *
   * @param em
   * @param nativeQuery
   * @param entityClass
   * @param params
   * @return
   */
  List<T> getResultListByNativeQuery(EntityManager em, String nativeQuery, Class<T> entityClass, Object... params);

  /**
   *
   * @param em
   * @param entityClass
   * @param range
   * @return
   */
  List<T> getResultListByRange(EntityManager em, Class<T> entityClass, int[] range);

  /**
   *
   * @param em
   * @param entity
   * @return
   */
  T persist(EntityManager em, T entity);

  /**
   *
   * @param em
   * @param entity
   */
  void merge(EntityManager em, T entity);

  /**
   *
   * @param em
   * @param entity
   */
  void remove(EntityManager em, T entity);

  /**
   *
   * @param em
   * @param id
   * @param entityClass
   */
  void remove(EntityManager em, K id, Class<T> entityClass);

  /**
   *
   * @param em
   * @param namedQuery
   * @param params
   * @return
   */
  int executeNamedQuery(EntityManager em, String namedQuery, Map<String, Object> params);

  /**
   *
   * @param em
   * @param jpqlQuery
   * @param params
   * @return
   */
  int executeJPQLQuery(EntityManager em, String jpqlQuery, Map<String, Object> params);

  /**
   *
   * @param em
   * @param nativeQuery
   * @param params
   * @return
   */
  int executeNativeQuery(EntityManager em, String nativeQuery, Object... params);

  /**
   *
   * @param em
   * @param entityClass
   * @return
   */
  int count(EntityManager em, Class<T> entityClass);
}
