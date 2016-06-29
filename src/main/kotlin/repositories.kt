package kr.iolo.samples

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
interface PostRepository : JpaRepository<Post, Long> {

}

@Component
interface PostMetaRepository : JpaRepository<PostMeta, Long> {

}

@Component
interface CommentRepository : JpaRepository<Comment, Long> {

}

@Component
interface CommentMetaRepository : JpaRepository<CommentMeta, Long> {

}

@Component
interface UserRepository : JpaRepository<User, Long> {

}

@Component
interface UserMetaRepository : JpaRepository<UserMeta, Long> {

}

@Component
interface TermRepository : JpaRepository<Term, Long> {

}

@Component
interface TermMetaRepository : JpaRepository<TermMeta, Long> {

}

@Component
interface TermTaxonomyRepository : JpaRepository<TermTaxonomy, Long> {

}

@Component
interface TermRelationshipRepository : JpaRepository<TermRelationship, TermRelationshipPK> {

}

@Component
interface LinkRepository : JpaRepository<Link, Long> {

}

@Component
interface OptionRepository : JpaRepository<Option, Long> {

}
